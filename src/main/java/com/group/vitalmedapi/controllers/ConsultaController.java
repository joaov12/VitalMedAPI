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

import com.group.vitalmedapi.models.Consulta;
import com.group.vitalmedapi.models.dtos.CreateConsultaDTO;
import com.group.vitalmedapi.services.ConsultaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("consultas")
@Tag(name = "Consulta")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @Operation(summary = "Obter todas as Consultas", description = "Retorna uma lista de todas as consultas cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Consulta>> getAllConsultas() {
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAll());
    }

    @Operation(summary = "Obter consulta por ID", description = "Retorna uma consulta com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Consulta> getConsultaById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(consultaService.findById(id));
    }

    @Operation(summary = "Adicionar consulta", description = "Adiciona uma nova consulta")
    @PostMapping("/add")
    public ResponseEntity<Consulta> addConsulta(@RequestBody Consulta consulta) {
       return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.addConsulta(consulta));
    }

    @Operation(summary = "Editar consulta", description = "Edita uma consulta existente")
    @PutMapping("/edit")
    public ResponseEntity<Consulta> editConsulta(@RequestBody Consulta consulta) {
       return ResponseEntity.status(HttpStatus.OK).body(consultaService.updateConsulta(consulta));
    }

    @Operation(summary = "Excluir consulta", description = "Exclui uma consulta com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteConsulta(@PathVariable("id") Long id) {
       consultaService.deleteConsulta(id); 
       return ResponseEntity.status(HttpStatus.OK).body("Consulta deletada com sucesso"); 
    }

    
    @Operation(summary = "Adicionar uma consulta usando IDs", description = "Cria consulta passando apenas Id do médico e paciente + informações necessárias")
    @PostMapping("/createWID")
    public ResponseEntity<?> createConsulta(@RequestBody CreateConsultaDTO dto) {
        Consulta consulta = consultaService.createConsulta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(consulta);
    }
}
