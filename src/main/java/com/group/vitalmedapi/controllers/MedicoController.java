package com.group.vitalmedapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.services.MedicoService;

@RestController
@RequestMapping("/funcionarios")
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @GetMapping("/all")
    public ResponseEntity<List<Medico>> getAllMedicos() {
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.findAll());
    }

    
}
