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

import com.group.vitalmedapi.models.Enfermeiro;
import com.group.vitalmedapi.services.EnfermeiroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/enfermeiros")
@Tag(name = "Enfermeiro")
public class EnfermeiroController {
    @Autowired
    EnfermeiroService enfermeiroService;

    @Operation(summary = "Obter todos os Enfermeiros", description = "Retorna uma lista de todos os enfermeiros cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Enfermeiro>> getAllEnfermeiros() {
        return ResponseEntity.status(HttpStatus.OK).body(enfermeiroService.findAll());
    }

    @Operation(summary = "Obter enfermeiro por ID", description = "Retorna um enfermeiro com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Enfermeiro> getEnfermeiroById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(enfermeiroService.findById(id));
    }

    @Operation(summary = "Adicionar enfermeiro", description = "Adiciona um novo enfermeiro")
    @PostMapping("/add")
    public ResponseEntity<Enfermeiro> addEnfermeiro(@RequestBody Enfermeiro enfermeiro) {
       return ResponseEntity.status(HttpStatus.CREATED).body(enfermeiroService.addEnfermeiro(enfermeiro));
    }

    @Operation(summary = "Editar enfermeiro", description = "Edita um enfermeiro existente")
    @PutMapping("/edit")
    public ResponseEntity<Enfermeiro> editEnfermeiro(@RequestBody Enfermeiro enfermeiro) {
       return ResponseEntity.status(HttpStatus.OK).body(enfermeiroService.updateEnfermeiro(enfermeiro));
    }

   @Operation(summary = "Excluir enfermeiro", description = "Exclui um enfermeiro com base no ID fornecido")
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteEnfermeiro(@PathVariable("id") Long id) {
      enfermeiroService.deleteEnfermeiro(id); 
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
   }
}
