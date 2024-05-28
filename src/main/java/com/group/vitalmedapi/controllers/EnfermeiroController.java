package com.group.vitalmedapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.vitalmedapi.models.Enfermeiro;
import com.group.vitalmedapi.services.EnfermeiroService;

@RestController
@RequestMapping("/enfermeiros")
public class EnfermeiroController {
    @Autowired
    EnfermeiroService enfermeiroService;

    @GetMapping("/all")
    public ResponseEntity<List<Enfermeiro>> getAllEnfermeiros() {
        return ResponseEntity.status(HttpStatus.OK).body(enfermeiroService.findAll());
    }
}
