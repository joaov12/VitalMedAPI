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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/all")
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Funcionario>> getFuncionarioById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findById(id));
    }
    
    @PostMapping("/add")
    public ResponseEntity<Funcionario> addFuncionario(@RequestBody Funcionario funcionario) {
       return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.addFuncionario(funcionario));
    }

    @PutMapping("/edit")
    public ResponseEntity<Funcionario> editFuncionario(@RequestBody Funcionario funcionario) {
       return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.updateFuncionario(funcionario));
    }

    
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteFuncionario(@PathVariable("id") Long id) {
      funcionarioService.deleteFuncionario(id); 
      return ResponseEntity.status(HttpStatus.OK).body("Funcionario deletado com sucesso");
   }
}
