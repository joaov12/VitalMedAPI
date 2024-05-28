package com.group.vitalmedapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
