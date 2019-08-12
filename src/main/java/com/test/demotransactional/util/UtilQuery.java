package com.test.demotransactional.util;

public class UtilQuery {

    public static final String queryListBarang = "SELECT id, nama, jumlah, harga FROM barang";
    public static final String queryInsertBarang = "INSERT into barang (id, nama, jumlah, harga) VALUES (?,?,?,?)";
    public static final String queryInsertTransaksi = "INSERT into transaksi (id, tanggal, kode_admin) VALUES (?,?,?)";
    public static final String insertQueryTransaksiBarang = "INSERT into transaksi_barang(transaksi_id, barang_id, jumlah, harga) VALUES (?,?,?,?)";
    public static final String listQueryTransaksi = "SELECT * FROM transaksi";
    public static final String queryBarangById = "SELECT * FROM barang where id=?";

    public static final String queryByIdTransaksi = "SELECT * FROM transaksi WHERE id = ?";
    public static final String queryByTransaksiBarangByTransaksiId = "SELECT * FROM transaksi_barang WHERE transaksi_id = ?";
    public static final String queryJoin = "select * from transaksi inner join transaksi_barang on (transaksi.id=transaksi_barang.transaksi_id)";
}
