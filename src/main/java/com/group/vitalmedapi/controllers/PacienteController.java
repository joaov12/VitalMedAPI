package com.group.vitalmedapi.controllers;

import java.util.List;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Paciente")
public class PacienteController {
    
    @Autowired
    PacienteService pacienteService;

    @Operation(summary = "Obter todos os pacientes", description = "Retorna uma lista de todos os pacientes cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findAll());
    }

    @Operation(summary = "Obter paciente por ID", description = "Retorna um paciente com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(pacienteService.findById(id));
    }

    @Operation(summary = "Adicionar paciente", description = "Adiciona um novo paciente")
    @PostMapping("/add")
    public ResponseEntity<Paciente> addPaciente(@RequestBody Paciente paciente) {
       return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.addPaciente(paciente));
    }
    
    @Operation(summary = "Editar paciente", description = "Edita um paciente existente")
    @PutMapping("/edit")
    public ResponseEntity<Paciente> editPaciente(@RequestBody Paciente paciente) {
       return ResponseEntity.status(HttpStatus.OK).body(pacienteService.updatePaciente(paciente));
    }

    
    @Operation(summary = "Excluir paciente", description = "Exclui um paciente com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable("id") Long id) {
       pacienteService.deletePaciente(id); 
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
