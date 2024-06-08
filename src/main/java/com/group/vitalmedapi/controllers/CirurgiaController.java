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

import com.group.vitalmedapi.models.Cirurgia;
import com.group.vitalmedapi.models.dtos.CreateCirurgiaDTO;
import com.group.vitalmedapi.services.CirurgiaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("cirurgias")
@Tag(name = "Cirurgia")
public class CirurgiaController {
    
    @Autowired
    CirurgiaService cirurgiaService;

    @Operation(summary = "Obter todas as Cirurgias", description = "Retorna uma lista de todas as cirurgias cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Cirurgia>> getAllCirurgias() {
        return ResponseEntity.status(HttpStatus.OK).body(cirurgiaService.findAll());
    }

    @Operation(summary = "Obter cirurgia por ID", description = "Retorna uma cirurgia com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Cirurgia> getCirurgiaById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(cirurgiaService.findById(id));
    }

    @Operation(summary = "Adicionar cirurgia", description = "Adiciona uma nova cirurgia")
    @PostMapping("/add")
    public ResponseEntity<Cirurgia> addCirurgia(@RequestBody Cirurgia cirurgia) {
       return ResponseEntity.status(HttpStatus.CREATED).body(cirurgiaService.addCirurgia(cirurgia));
    }

    @Operation(summary = "Editar cirurgia", description = "Edita uma cirurgia existente")
    @PutMapping("/edit")
    public ResponseEntity<Cirurgia> editCirurgia(@RequestBody Cirurgia cirurgia) {
       return ResponseEntity.status(HttpStatus.OK).body(cirurgiaService.updateCirurgia(cirurgia));
    }

    @Operation(summary = "Excluir cirurgia", description = "Exclui uma cirurgia com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCirurgia(@PathVariable("id") Long id) {
       cirurgiaService.deleteCirurgia(id); 
       return ResponseEntity.status(HttpStatus.OK).body("Cirurgia deletada com sucesso"); 
    }

    @Operation(summary = "Adicionar uma cirurgia usando IDs", description = "Cria cirurgia passando apenas IDs(Médico, Paciente, Lista de ID de enfermeiros) + informações necessárias")
    @PostMapping("/createWID")
    public Cirurgia criarCirurgia(@RequestBody CreateCirurgiaDTO createCirurgiaDTO) {
        return cirurgiaService.createCirurgia(createCirurgiaDTO);
    }
}
