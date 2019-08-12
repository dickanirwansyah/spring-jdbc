package com.test.demotransactional.services;

import com.test.demotransactional.mapper.MapperTransaksiBarang;
import com.test.demotransactional.mapper.TransaksiBarangMapper;
import com.test.demotransactional.model.Barang;
import com.test.demotransactional.model.BarangTransaksi;
import com.test.demotransactional.model.Transaksi;
import com.test.demotransactional.model.TransaksiBarang;
import com.test.demotransactional.util.UtilQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransaksiService {

    private static final Logger log = LoggerFactory.getLogger(TransaksiService.class);

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private BarangService barangService;

    public TransaksiService(JdbcTemplate jdbc, BarangService barangService) {
        this.jdbc = jdbc;
        this.barangService = barangService;
    }

    @Transactional
    public void insertTransaksi(Transaksi transaksi){

        List<TransaksiBarang> transaksiBarangs = null;

        try{

            jdbc.update(UtilQuery.queryInsertTransaksi, preparedStatement -> {

                preparedStatement.setString(1, transaksi.getId());
                preparedStatement.setDate(2, new Date(transaksi.getTanggal().getTime()));
                preparedStatement.setString(3, transaksi.getKodeAdmin());

            });

            transaksiBarangs = transaksi.getTransaksiBarangs();
            for (TransaksiBarang transaksiBarang : transaksiBarangs){
                jdbc.update(UtilQuery.insertQueryTransaksiBarang, preparedStatement -> {

                    preparedStatement.setString(1, transaksiBarang.getTransaksi().getId());
                    preparedStatement.setString(2, transaksiBarang.getBarang().getId());
                    preparedStatement.setInt(3, transaksiBarang.getJumlah());
                    preparedStatement.setLong(4, transaksiBarang.getHarga());

                });
            }

            transaksi.setTransaksiBarangs(transaksiBarangs);
            log.info("TEST INSERT TRANSAKSI : "+String.format(" | %s | %s | %s | ",
                    transaksi.getId(),
                    transaksi.getTanggal(),
                    transaksi.getKodeAdmin()));

            log.info("TEST INSERT TRANSAKSI BARANG : "+transaksiBarangs.toString());

        }catch (RuntimeException e){
            log.error(e.getMessage());
        }


        new Transaksi(transaksi.getId(),
                transaksi.getTanggal(),
                transaksi.getKodeAdmin(), transaksiBarangs);
    }


    public List<Transaksi> getTransaksi(){

        List<Transaksi> responseTransaksi = new ArrayList<>();

        List<Transaksi> queryTransaksis =
                jdbc.query(UtilQuery.listQueryTransaksi, new RowMapper<Transaksi>() {
                    @Override
                    public Transaksi mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Transaksi(
                                resultSet.getString("id"),
                                resultSet.getDate("tanggal"),
                                resultSet.getString("kode_admin")
                        );
                    }
                });


        return queryTransaksis;
    }

    public List<BarangTransaksi> barangTransaksis(){
        List<BarangTransaksi> listBarangTransaksi = new ArrayList();

        String sql = UtilQuery.queryJoin;

        List<BarangTransaksi> listTransaksiBarang = jdbc.query(sql, new RowMapper<BarangTransaksi>() {
            @Override
            public BarangTransaksi mapRow(ResultSet resultSet, int i) throws SQLException {
              BarangTransaksi barangTransaksi = new BarangTransaksi();
              Transaksi transaksi = new Transaksi();
              transaksi.setId(resultSet.getString("id"));
              transaksi.setTanggal(resultSet.getDate("tanggal"));
              transaksi.setKodeAdmin(resultSet.getString("kode_admin"));
              barangTransaksi.setTransaksi(transaksi);
                TransaksiBarang transaksiBarang = new TransaksiBarang();
                transaksiBarang.setTransaksi(transaksi);
                String barangId = resultSet.getString("barang_id");
                Barang objectBarang = barangService.barangGetId(barangId);
                transaksiBarang.setBarang(objectBarang);
                transaksiBarang.setJumlah(resultSet.getInt("jumlah"));
                transaksiBarang.setHarga(resultSet.getLong("harga"));
                barangTransaksi.setTransaksiBarang(transaksiBarang);
                listBarangTransaksi.add(barangTransaksi);
                return barangTransaksi;
            }
        });

        log.info("LIST TRANSAKSI BARANG : "+listTransaksiBarang);
        return listTransaksiBarang;
    }


    public List<Transaksi> getTransaksiAndBarangTransaksi(){

        List<Transaksi> responseListTransaksi = new ArrayList<>();

        List<Transaksi> transaksis = jdbc.query("SELECT * FROM Transaksi", new RowMapper<Transaksi>() {
            @Override
            public Transaksi mapRow(ResultSet resultSet, int i) throws SQLException {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(resultSet.getString("id"));
                transaksi.setKodeAdmin(resultSet.getString("kode_admin"));
                transaksi.setTanggal(resultSet.getDate("tanggal"));
                return transaksi;
            }
        });

        List<TransaksiBarang> transaksiBarangs = new ArrayList<>();
        for (Transaksi transaksi : transaksis){
            Transaksi tr = new Transaksi();
            tr.setId(transaksi.getId());
            tr.setKodeAdmin(transaksi.getKodeAdmin());
            tr.setTanggal(transaksi.getTanggal());
            transaksiBarangs = getTransaksi(transaksi.getId());
            tr.setTransaksiBarangs(transaksiBarangs);
            responseListTransaksi.add(tr);
        }

        return responseListTransaksi;
    }

    private List<TransaksiBarang> getTransaksi(String id){
        String sql = "select transaksi_id, barang_id, jumlah, harga from transaksi_barang where transaksi_id = ?";
        List<TransaksiBarang> transaksiBarangs = jdbc.query(sql, new Object[]{id},new RowMapper<TransaksiBarang>() {
            @Override
            public TransaksiBarang mapRow(ResultSet resultSet, int i) throws SQLException {
                TransaksiBarang transaksiBarang = new TransaksiBarang();

                String transaksiId = resultSet.getString("transaksi_id");
                Transaksi transaksi = getId(transaksiId);
                transaksiBarang.setTransaksi(transaksi);

                String barangId = resultSet.getString("barang_id");
                Barang barang = barangService.barangGetId(barangId);
                transaksiBarang.setBarang(barang);

                transaksiBarang.setJumlah(resultSet.getInt("jumlah"));
                transaksiBarang.setHarga(resultSet.getLong("harga"));
                return transaksiBarang;
            }
        });

        return transaksiBarangs;
    }

    private Transaksi getId(String id){
        return jdbc.queryForObject("select * from transaksi where id = ?", new Object[]{id}, new RowMapper<Transaksi>() {
            @Override
            public Transaksi mapRow(ResultSet resultSet, int i) throws SQLException {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(resultSet.getString("id"));
                transaksi.setTanggal(resultSet.getDate("tanggal"));
                transaksi.setKodeAdmin(resultSet.getString("kode_admin"));
                return transaksi;
            }
        });
    }


    public List<MapperTransaksiBarang> getTransaksiBarangs(){

        List<MapperTransaksiBarang> querys = jdbc.query("select * from transaksi_barang", new TransaksiBarangMapper());
        return querys;
    }


}
