package com.test.demotransactional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TransaksiBarang {

    @JsonIgnore
    private Transaksi transaksi;
    private Barang barang;
    private int jumlah;
    private long harga;

    public TransaksiBarang(){}

    public TransaksiBarang(Transaksi transaksi, Barang barang, int jumlah, long harga) {
        this.transaksi = transaksi;
        this.barang = barang;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "TransaksiBarang{" +
                "transaksi=" + transaksi +
                ", barang=" + barang +
                ", jumlah=" + jumlah +
                ", harga=" + harga +
                '}';
    }
}
