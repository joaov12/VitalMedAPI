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

import com.group.vitalmedapi.models.Enfermeiro;
import com.group.vitalmedapi.services.EnfermeiroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/enfermeiros")
@Tag(name = "Enfermeiro")
public class EnfermeiroController {

    @Autowired
    private EnfermeiroService enfermeiroService;

    @Operation(summary = "Obter todos os Enfermeiros", description = "Retorna uma lista de todos os enfermeiros cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de enfermeiros retornada com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<List<EntityModel<Enfermeiro>>> getAllEnfermeiros() {
        List<Enfermeiro> enfermeiros = enfermeiroService.findAll();

        List<EntityModel<Enfermeiro>> enfermeiroModels = enfermeiros.stream()
                .map(enfermeiro -> {
                    EntityModel<Enfermeiro> enfermeiroModel = EntityModel.of(enfermeiro);
                    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnfermeiroController.class).getEnfermeiroById(enfermeiro.getId())).withSelfRel();
                    enfermeiroModel.add(selfLink);
                    return enfermeiroModel;
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(enfermeiroModels);
    }

    @Operation(summary = "Obter enfermeiro por ID", description = "Retorna um enfermeiro com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enfermeiro retornado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Enfermeiro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<EntityModel<Enfermeiro>> getEnfermeiroById(@PathVariable("id") Long id) {
        Enfermeiro enfermeiro = enfermeiroService.findById(id);

        EntityModel<Enfermeiro> enfermeiroModel = EntityModel.of(enfermeiro);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnfermeiroController.class).getEnfermeiroById(id)).withSelfRel();
        enfermeiroModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(enfermeiroModel);
    }

    @Operation(summary = "Adicionar enfermeiro", description = "Adiciona um novo enfermeiro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enfermeiro criado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/add")
    public ResponseEntity<EntityModel<Enfermeiro>> addEnfermeiro(@RequestBody Enfermeiro enfermeiro) {
        Enfermeiro novoEnfermeiro = enfermeiroService.addEnfermeiro(enfermeiro);

        EntityModel<Enfermeiro> enfermeiroModel = EntityModel.of(novoEnfermeiro);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnfermeiroController.class).getEnfermeiroById(novoEnfermeiro.getId())).withSelfRel();
        enfermeiroModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(enfermeiroModel);
    }

    @Operation(summary = "Editar enfermeiro", description = "Edita um enfermeiro existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enfermeiro atualizado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Enfermeiro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/edit")
    public ResponseEntity<EntityModel<Enfermeiro>> editEnfermeiro(@RequestBody Enfermeiro enfermeiro) {
        Enfermeiro enfermeiroAtualizado = enfermeiroService.updateEnfermeiro(enfermeiro);

        EntityModel<Enfermeiro> enfermeiroModel = EntityModel.of(enfermeiroAtualizado);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnfermeiroController.class).getEnfermeiroById(enfermeiroAtualizado.getId())).withSelfRel();
        enfermeiroModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(enfermeiroModel);
    }

    @Operation(summary = "Excluir enfermeiro", description = "Exclui um enfermeiro com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Enfermeiro excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Enfermeiro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEnfermeiro(@PathVariable("id") Long id) {
        enfermeiroService.deleteEnfermeiro(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
