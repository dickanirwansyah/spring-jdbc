package com.test.demotransactional.mapper;

import com.test.demotransactional.model.Barang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BarangMapper implements RowMapper<Barang> {

    @Override
    public Barang mapRow(ResultSet resultSet, int i) throws SQLException {
        Barang barang = new Barang();
        barang.setId(resultSet.getString("id"));
        barang.setNama(resultSet.getString("nama"));
        barang.setJumlah(resultSet.getInt("jumlah"));
        barang.setHarga(resultSet.getLong("harga"));
        return barang;
    }
}
