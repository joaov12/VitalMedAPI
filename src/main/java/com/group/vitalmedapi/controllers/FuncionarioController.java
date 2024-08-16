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

import com.group.vitalmedapi.models.Funcionario;
import com.group.vitalmedapi.services.FuncionarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/funcionarios")
@Tag(name = "Funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(summary = "Obter todos os Funcionarios", description = "Retorna uma lista de todos os funcionários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de funcionários retornada com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<List<EntityModel<Funcionario>>> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.findAll();

        List<EntityModel<Funcionario>> funcionarioModels = funcionarios.stream()
                .map(funcionario -> {
                    EntityModel<Funcionario> funcionarioModel = EntityModel.of(funcionario);
                    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).getFuncionarioById(funcionario.getId())).withSelfRel();
                    funcionarioModel.add(selfLink);
                    return funcionarioModel;
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioModels);
    }

    @Operation(summary = "Obter Funcionario por ID", description = "Retorna um funcionário com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário retornado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<EntityModel<Funcionario>> getFuncionarioById(@PathVariable("id") Long id) {
        Funcionario funcionario = funcionarioService.findById(id);

        EntityModel<Funcionario> funcionarioModel = EntityModel.of(funcionario);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FuncionarioController.class).getFuncionarioById(id)).withSelfRel();
        funcionarioModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioModel);
    }
}
