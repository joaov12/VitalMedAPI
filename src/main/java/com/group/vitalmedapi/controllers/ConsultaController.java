package com.group.vitalmedapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.vitalmedapi.models.Consulta;
import com.group.vitalmedapi.services.ConsultaService;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @GetMapping("/all")
    public ResponseEntity<List<Consulta>> getAllConsultas() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAll());
    }

}
