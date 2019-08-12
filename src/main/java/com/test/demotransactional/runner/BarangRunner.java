package com.test.demotransactional.runner;

import com.test.demotransactional.model.Barang;
import com.test.demotransactional.services.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BarangRunner implements CommandLineRunner {

    @Autowired
    private BarangService barangService;

    @Override
    public void run(String... args) throws Exception {
        Barang barang1 = new Barang();
        barang1.setId("01");
        barang1.setNama("COKLAT");
        barang1.setJumlah(10);
        barang1.setHarga(80000l);
        barangService.insertBarang(barang1);

        Barang barang2 = new Barang();
        barang2.setId("02");
        barang2.setNama("VANILA");
        barang2.setHarga(90000l);
        barangService.insertBarang(barang2);
    }
}
