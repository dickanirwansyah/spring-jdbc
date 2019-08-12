package com.test.demotransactional.controller;

import com.test.demotransactional.mapper.MapperTransaksiBarang;
import com.test.demotransactional.model.Transaksi;
import com.test.demotransactional.services.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/transaksi")
public class TransaksiController {
    
    @Autowired
    private TransaksiService transaksiService;

    @GetMapping
    public List<Transaksi> listTransaksi(){
        return transaksiService.getTransaksi();
    }

    @GetMapping(value = "/transaksi-barang")
    public List<MapperTransaksiBarang> getTransaksiBarang(){
        return transaksiService.getTransaksiBarangs();
    }

    @GetMapping(value = "/transaksis")
    public List<Transaksi> getTransaksis(){
        return transaksiService.getTransaksiAndBarangTransaksi();
    }

    // TODO: 12/08/19 will create transaksi controller 

    // TODO: 12/08/19 will create transaksi insert controller 

    // TODO: 12/08/19 will create transaksi by id controller 
}
