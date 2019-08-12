package com.test.demotransactional.model;

import java.io.Serializable;

public class Barang implements Serializable {

    private String id;
    private String nama;
    private int jumlah;
    private long harga;

    public Barang(){}

    public Barang(String id, String nama, int jumlah, long harga) {
        this.id = id;
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
        return "Barang{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", jumlah=" + jumlah +
                ", harga=" + harga +
                '}';
    }
}
