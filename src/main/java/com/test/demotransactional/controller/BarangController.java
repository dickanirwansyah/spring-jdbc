package com.test.demotransactional.controller;

import com.test.demotransactional.model.Barang;
import com.test.demotransactional.services.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/barang")
public class BarangController {

    @Autowired
    private BarangService barangService;

    @PostMapping
    public ResponseEntity<Barang> createBarang(@RequestBody Barang barang){
        return ResponseEntity.ok(barangService.insertBarang(barang));
    }

    @GetMapping
    public ResponseEntity<List<Barang>> getBarangs(){
        List<Barang> barangs = barangService.listBarang();
        if (barangs.isEmpty() || barangs == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(barangs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Barang> getId(@PathVariable("id") String id){
        Barang barang = barangService.barangGetId(id);
        if (barang == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(barang, HttpStatus.OK);
    }

}
