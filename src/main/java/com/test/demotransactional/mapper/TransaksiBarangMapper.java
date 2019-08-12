package com.test.demotransactional.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransaksiBarangMapper implements RowMapper<MapperTransaksiBarang> {

    @Override
    public MapperTransaksiBarang mapRow(ResultSet resultSet, int i) throws SQLException {
        MapperTransaksiBarang mtb = new MapperTransaksiBarang();
        mtb.setTransaksiId(resultSet.getString("transaksi_id"));
        mtb.setBarangId(resultSet.getString("barang_id"));
        mtb.setJumlah(resultSet.getInt("jumlah"));
        mtb.setHarga(resultSet.getLong("harga"));
        return mtb;
    }
}
