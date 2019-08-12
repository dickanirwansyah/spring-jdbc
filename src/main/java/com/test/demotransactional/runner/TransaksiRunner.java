package com.test.demotransactional.runner;

import com.test.demotransactional.model.Barang;
import com.test.demotransactional.model.Transaksi;
import com.test.demotransactional.model.TransaksiBarang;
import com.test.demotransactional.services.BarangService;
import com.test.demotransactional.services.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TransaksiRunner implements CommandLineRunner {

    @Autowired
    private TransaksiService transaksiService;

    @Autowired
    private BarangService barangService;

    @Override
    public void run(String... args) throws Exception {

        Transaksi transaksi1 = new Transaksi();
        Transaksi transaksi2 = new Transaksi();
        Barang barang1 = barangService.barangGetId("01");
        Barang barang2 = barangService.barangGetId("02");

        transaksi1.setId("trans001");
        transaksi1.setKodeAdmin("ka001");
        transaksi1.setTanggal(new Date());

        transaksi2.setId("trans002");
        transaksi2.setKodeAdmin("ka002");
        transaksi2.setTanggal(new Date());

        List<TransaksiBarang> transaksiBarangs1 = new ArrayList<>();
        List<TransaksiBarang> transaksiBarangs2 = new ArrayList<>();

        TransaksiBarang transaksiBarang2 = new TransaksiBarang();
        transaksiBarang2.setTransaksi(transaksi2);
        transaksiBarang2.setBarang(barang2);
        transaksiBarang2.setHarga(barang2.getHarga());
        transaksiBarang2.setJumlah(9);

        TransaksiBarang transaksiBarang3 = new TransaksiBarang();
        transaksiBarang3.setTransaksi(transaksi2);
        transaksiBarang3.setBarang(barang1);
        transaksiBarang3.setHarga(barang1.getHarga());
        transaksiBarang3.setJumlah(5);

        transaksiBarangs2.add(transaksiBarang2);
        transaksiBarangs2.add(transaksiBarang3);

        for (int i=0; i < 2; i++){
            TransaksiBarang transaksiBarang = new TransaksiBarang();
            transaksiBarang.setTransaksi(transaksi1);
            transaksiBarang.setBarang(barang1);
            transaksiBarang.setHarga(barang1.getHarga());
            transaksiBarang.setJumlah(69);

            transaksiBarangs1.add(transaksiBarang);
        }

        transaksi1.setTransaksiBarangs(transaksiBarangs1);
        transaksi2.setTransaksiBarangs(transaksiBarangs2);

        transaksiService.insertTransaksi(transaksi1);
        transaksiService.insertTransaksi(transaksi2);

        //list transaksi
        transaksiService.barangTransaksis();
    }
}
