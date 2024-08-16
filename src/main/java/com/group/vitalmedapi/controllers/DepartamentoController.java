package com.group.vitalmedapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group.vitalmedapi.dtos.AddFuncionarioToDepartamentoDTO;
import com.group.vitalmedapi.models.Departamento;
import com.group.vitalmedapi.services.DepartamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("departamentos")
@Tag(name = "Departamento")
public class DepartamentoController {

   @Autowired
   DepartamentoService departamentoService;

   @Operation(summary = "Obter todos os Departamentos", description = "Retorna uma lista de todos os departamentos cadastrados")
   @GetMapping("/all")
   public ResponseEntity<List<EntityModel<Departamento>>> getAllDepartamentos() {
      List<Departamento> departamentos = departamentoService.findAll();

      List<EntityModel<Departamento>> departamentoModels = departamentos.stream()
              .map(departamento -> {
                 EntityModel<Departamento> departamentoModel = EntityModel.of(departamento);
                 Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartamentoController.class).getDepartamentoById(departamento.getId())).withSelfRel();
                 departamentoModel.add(selfLink);
                 return departamentoModel;
              })
              .collect(Collectors.toList());

      return ResponseEntity.status(HttpStatus.OK).body(departamentoModels);
   }

   @Operation(summary = "Obter departamento por ID", description = "Retorna um departamento com base no ID fornecido")
   @GetMapping("/find/{id}")
   public ResponseEntity<EntityModel<Departamento>> getDepartamentoById(@PathVariable("id") Long id) {
      Departamento departamento = departamentoService.findById(id);

      EntityModel<Departamento> departamentoModel = EntityModel.of(departamento);
      Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartamentoController.class).getDepartamentoById(id)).withSelfRel();
      departamentoModel.add(selfLink);

      return ResponseEntity.status(HttpStatus.OK).body(departamentoModel);
   }

   @Operation(summary = "Adicionar departamento", description = "Adiciona um novo departamento")
   @PostMapping("/add")
   public ResponseEntity<EntityModel<Departamento>> addDepartamento(@RequestBody Departamento departamento) {
      Departamento novoDepartamento = departamentoService.addDepartamento(departamento);

      EntityModel<Departamento> departamentoModel = EntityModel.of(novoDepartamento);
      Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartamentoController.class).getDepartamentoById(novoDepartamento.getId())).withSelfRel();
      departamentoModel.add(selfLink);

      return ResponseEntity.status(HttpStatus.CREATED).body(departamentoModel);
   }

   @Operation(summary = "Editar departamento", description = "Edita um departamento existente")
   @PutMapping("/edit")
   public ResponseEntity<EntityModel<Departamento>> editDepartamento(@RequestBody Departamento departamento) {
      Departamento departamentoAtualizado = departamentoService.updateDepartamento(departamento);

      EntityModel<Departamento> departamentoModel = EntityModel.of(departamentoAtualizado);
      Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartamentoController.class).getDepartamentoById(departamentoAtualizado.getId())).withSelfRel();
      departamentoModel.add(selfLink);

      return ResponseEntity.status(HttpStatus.OK).body(departamentoModel);
   }

   @Operation(summary = "Excluir departamento", description = "Exclui um departamento com base no ID fornecido")
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteDepartamento(@PathVariable("id") Long id) {
      departamentoService.deleteDepartamento(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   @Operation(summary = "Adicionar Funcionario a um departamento", description = "Vincula um funcionario a um departamento")
   @PutMapping("/addFuncionario")
   public ResponseEntity<EntityModel<Departamento>> addFuncionarioToDepartamento(@RequestBody AddFuncionarioToDepartamentoDTO dto) {
      Departamento departamento = departamentoService.addFuncionarioToDepartamento(dto);

      EntityModel<Departamento> departamentoModel = EntityModel.of(departamento);
      Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartamentoController.class).getDepartamentoById(departamento.getId())).withSelfRel();
      departamentoModel.add(selfLink);

      return ResponseEntity.ok(departamentoModel);
   }
}
