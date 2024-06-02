package com.group.vitalmedapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.vitalmedapi.models.Cirurgia;
import com.group.vitalmedapi.services.CirurgiaService;

@RestController
@RequestMapping("cirurgias")
public class CirurgiaController {
    
    @Autowired
    CirurgiaService cirurgiaService;

    @GetMapping("/all")
    public ResponseEntity<List<Cirurgia>> getAllCirurgias() {
        return ResponseEntity.status(HttpStatus.OK).body(cirurgiaService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Cirurgia>> getCirurgiaById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(cirurgiaService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Cirurgia> addCirurgia(@RequestBody Cirurgia cirurgia) {
       return ResponseEntity.status(HttpStatus.CREATED).body(cirurgiaService.addCirurgia(cirurgia));
    }


}
