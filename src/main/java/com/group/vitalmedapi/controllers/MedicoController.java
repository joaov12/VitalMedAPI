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

import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.services.MedicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/medicos")
@Tag(name = "Medico")
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @Operation(summary = "Obter todos os Médicos", description = "Retorna uma lista de todos os médicos cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Medico>> getAllMedicos() {
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.findAll());
    }

    @Operation(summary = "Obter médico por ID", description = "Retorna um médico com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Medico>> getMedicoById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(medicoService.findById(id));
    }

    @Operation(summary = "Adicionar médico", description = "Adiciona um novo médico")
    @PostMapping("/add")
    public ResponseEntity<Medico> addMedico(@RequestBody Medico medico) {
       return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.addMedico(medico));
    }

    @Operation(summary = "Editar médico", description = "Edita um médico existente")
    @PutMapping("/edit")
    public ResponseEntity<Medico> editMedico(@RequestBody Medico medico) {
       return ResponseEntity.status(HttpStatus.OK).body(medicoService.updateMedico(medico));
    }

    @Operation(summary = "Excluir médico", description = "Exclui um médico com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable("id") Long id) {
       medicoService.deleteMedico(id); 
       return ResponseEntity.status(HttpStatus.OK).body("Medico deletado com sucesso");
    }

}
