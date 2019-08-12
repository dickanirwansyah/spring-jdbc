package com.test.demotransactional.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Transaksi implements Serializable {

    private String id;
    private Date tanggal;
    private String kodeAdmin;
    private List<TransaksiBarang> transaksiBarangs;

    public Transaksi(){}

    public Transaksi(String id, Date tanggal, String kodeAdmin){
        this.id = id;
        this.tanggal = tanggal;
        this.kodeAdmin = kodeAdmin;
    }

    public Transaksi(String id, Date tanggal, String kodeAdmin, List<TransaksiBarang> transaksiBarangs){
        this.id = id;
        this.tanggal = tanggal;
        this.kodeAdmin = kodeAdmin;
        this.transaksiBarangs = transaksiBarangs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodeAdmin() {
        return kodeAdmin;
    }

    public void setKodeAdmin(String kodeAdmin) {
        this.kodeAdmin = kodeAdmin;
    }

    public List<TransaksiBarang> getTransaksiBarangs() {
        return transaksiBarangs;
    }

    public void setTransaksiBarangs(List<TransaksiBarang> transaksiBarangs) {
        this.transaksiBarangs = transaksiBarangs;
    }

}
