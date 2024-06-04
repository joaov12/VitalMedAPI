package com.group.vitalmedapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.vitalmedapi.models.Funcionario;
import com.group.vitalmedapi.services.FuncionarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/funcionarios")
@Tag(name = "Funcionario")
public class FuncionarioController {
    
    @Autowired
    FuncionarioService funcionarioService;

    @Operation(summary = "Obter todos os Funcionarios", description = "Retorna uma lista de todos os funcionarios cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll());
    }

    @Operation(summary = "Obter Funcionario por ID", description = "Retorna um funcionario com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Funcionario>> getFuncionarioById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findById(id));
    }
}
