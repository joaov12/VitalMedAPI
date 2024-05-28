package com.group.vitalmedapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.vitalmedapi.models.Departamento;
import com.group.vitalmedapi.services.DepartamentoService;

@RestController
@RequestMapping("departamentos")
public class DepartamentoController {

    @Autowired
    DepartamentoService departamentoService;

    @GetMapping("/all")
    public ResponseEntity<List<Departamento>> getAllDepartamentos() {
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Departamento>> getDepartamentoById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findById(id));
    }
    
    @PostMapping("/add")
    public ResponseEntity<Departamento> addDepartamento(@RequestBody Departamento departamento) {
       return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.addDepartamento(departamento));
    }

    @PutMapping("/edit")
    public ResponseEntity<Departamento> editDepartamento(@RequestBody Departamento departamento) {
       return ResponseEntity.status(HttpStatus.OK).body(departamentoService.updateDepartamento(departamento));
    }

}
