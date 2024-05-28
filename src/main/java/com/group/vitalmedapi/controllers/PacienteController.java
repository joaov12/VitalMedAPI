package com.group.vitalmedapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.vitalmedapi.models.Paciente;
import com.group.vitalmedapi.services.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    
    @Autowired
    PacienteService pacienteService;

    
    @GetMapping("/all")
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findAll());
    }

}
