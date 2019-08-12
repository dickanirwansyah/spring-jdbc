package com.test.demotransactional.services;

import com.test.demotransactional.mapper.BarangMapper;
import com.test.demotransactional.model.Barang;
import com.test.demotransactional.util.UtilQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class BarangService {

    private static final Logger log = LoggerFactory.getLogger(BarangService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Barang> listBarang(){
        List<Barang> queryBarangs = jdbcTemplate.query(UtilQuery.queryListBarang, new RowMapper<Barang>() {

            @Override
            public Barang mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Barang(resultSet.getString("id"),
                        resultSet.getString("nama"),
                        resultSet.getInt("jumlah"),
                        resultSet.getLong("harga"));
            }
        });
        log.info("listBarangs : "+queryBarangs.toString());
        return queryBarangs;
    }

    public Barang barangGetId(String id){

        Barang barang = jdbcTemplate.queryForObject(UtilQuery.queryBarangById, new Object[]{id}, new BarangMapper());

        if (barang == null){
            log.info("barang kosong");
            return null;
        }

        return barang;
    }

    public Barang insertBarang(Barang barang){

         jdbcTemplate.update(UtilQuery.queryInsertBarang, preparedStatement -> {
            preparedStatement.setString(1, barang.getId());
            preparedStatement.setString(2, barang.getNama());
            preparedStatement.setInt(3, barang.getJumlah());
            preparedStatement.setLong(4, barang.getHarga());
        });

        log.info("insertBarang : "+barang.toString());

         return new Barang(barang.getId(),
                 barang.getNama(), barang.getJumlah(), barang.getHarga());

    }
}
