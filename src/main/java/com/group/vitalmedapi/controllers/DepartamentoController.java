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

import com.group.vitalmedapi.models.Departamento;
import com.group.vitalmedapi.models.dtos.AddFuncionarioToDepartamentoDTO;
import com.group.vitalmedapi.services.DepartamentoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("departamentos")
public class DepartamentoController {

    @Autowired
    DepartamentoService departamentoService;
    
    @Operation(summary = "Obter todos os Departamentos", description = "Retorna uma lista de todos os departamentos cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<Departamento>> getAllDepartamentos() {
        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findAll());
    }

    @Operation(summary = "Obter departamento por ID", description = "Retorna um departamento com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Departamento>> getDepartamentoById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(departamentoService.findById(id));
    }
    
    @Operation(summary = "Adicionar departamento", description = "Adiciona um novo departamento")
    @PostMapping("/add")
    public ResponseEntity<Departamento> addDepartamento(@RequestBody Departamento departamento) {
       return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.addDepartamento(departamento));
    }

    @Operation(summary = "Editar departamento", description = "Edita um departamento existente")
    @PutMapping("/edit")
    public ResponseEntity<Departamento> editDepartamento(@RequestBody Departamento departamento) {
       return ResponseEntity.status(HttpStatus.OK).body(departamentoService.updateDepartamento(departamento));
    }

   @Operation(summary = "Excluir departamento", description = "Exclui um departamento com base no ID fornecido")
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteDepartamento(@PathVariable("id") Long id) {
      departamentoService.deleteDepartamento(id); 
      return ResponseEntity.status(HttpStatus.OK).body("Departamento deletado com sucesso"); // Todo: Mudar a mensagem, deixar uma padrão
   }

   @Operation(summary = "Adicionar Funcionario a um departamento", description = "Vincula um funcionario a um departamento")
   @PutMapping("/addFuncionario")
   public ResponseEntity<Departamento> addFuncionarioToDepartamento(@RequestBody AddFuncionarioToDepartamentoDTO dto) {
       Departamento departamento = departamentoService.addFuncionarioToDepartamento(dto);
       return ResponseEntity.ok(departamento);
   }
}
