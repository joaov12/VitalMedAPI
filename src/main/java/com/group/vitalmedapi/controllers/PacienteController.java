package com.group.vitalmedapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Paciente>> getPacienteById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Paciente> addPaciente(@RequestBody Paciente paciente) {
       return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.addPaciente(paciente));
    }
    
    @PutMapping("/edit")
    public ResponseEntity<Paciente> editPaciente(@RequestBody Paciente paciente) {
       return ResponseEntity.status(HttpStatus.OK).body(pacienteService.updatePaciente(paciente));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable("id") Long id) {
       pacienteService.deletePaciente(id); 
       return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado com sucesso");
    }
}
