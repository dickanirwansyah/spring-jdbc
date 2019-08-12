package com.test.demotransactional.model;

public class BarangTransaksi {

    private Transaksi transaksi;
    private TransaksiBarang transaksiBarang;

    public BarangTransaksi(){}

    public BarangTransaksi(Transaksi transaksi, TransaksiBarang transaksiBarang){
        this.transaksi = transaksi;
        this.transaksiBarang = transaksiBarang;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public TransaksiBarang getTransaksiBarang() {
        return transaksiBarang;
    }

    public void setTransaksiBarang(TransaksiBarang transaksiBarang) {
        this.transaksiBarang = transaksiBarang;
    }
}
