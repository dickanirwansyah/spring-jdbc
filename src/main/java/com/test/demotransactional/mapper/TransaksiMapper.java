package com.test.demotransactional.mapper;

import com.test.demotransactional.model.Barang;
import com.test.demotransactional.model.Transaksi;
import com.test.demotransactional.model.TransaksiBarang;
import com.test.demotransactional.services.BarangService;
import com.test.demotransactional.services.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransaksiMapper {

}

//    @Autowired
//    private BarangService barangService;
//
//    @Autowired
//    private TransaksiService transaksiService;
//
//    @Override
//    public TransaksiBarang mapRow(ResultSet resultSet, int i) throws SQLException {
//        TransaksiBarang transaksiBarang = new TransaksiBarang();
//        String transaksiId = resultSet.getString("transaksi_id");
//        String barangId = resultSet.getString("barang_id");
//        Barang barang = barangService.barangGetId(barangId);
//        Transaksi transaksi = transaksiService.getId(transaksiId);
//        transaksiBarang.setTransaksi(transaksi);
//        transaksiBarang.setBarang(barang);
//        transaksiBarang.setJumlah(resultSet.getInt("jumlah"));
//        transaksiBarang.setHarga(resultSet.getLong("harga"));
//        return transaksiBarang;
//    }
//}
