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

import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.services.MedicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/medicos")
@Tag(name = "Medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Operation(summary = "Obter todos os Médicos", description = "Retorna uma lista de todos os médicos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de médicos retornada com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<List<EntityModel<Medico>>> getAllMedicos() {
        List<Medico> medicos = medicoService.findAll();

        List<EntityModel<Medico>> medicoModels = medicos.stream()
                .map(medico -> {
                    EntityModel<Medico> medicoModel = EntityModel.of(medico);
                    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).getMedicoById(medico.getId())).withSelfRel();
                    medicoModel.add(selfLink);
                    return medicoModel;
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(medicoModels);
    }

    @Operation(summary = "Obter médico por ID", description = "Retorna um médico com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico retornado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<EntityModel<Medico>> getMedicoById(@PathVariable("id") Long id) {
        Medico medico = medicoService.findById(id);

        EntityModel<Medico> medicoModel = EntityModel.of(medico);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).getMedicoById(id)).withSelfRel();
        medicoModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(medicoModel);
    }

    @Operation(summary = "Adicionar médico", description = "Adiciona um novo médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico criado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/add")
    public ResponseEntity<EntityModel<Medico>> addMedico(@RequestBody Medico medico) {
        Medico createdMedico = medicoService.addMedico(medico);

        EntityModel<Medico> medicoModel = EntityModel.of(createdMedico);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).getMedicoById(createdMedico.getId())).withSelfRel();
        medicoModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(medicoModel);
    }

    @Operation(summary = "Editar médico", description = "Edita um médico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico atualizado com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/edit")
    public ResponseEntity<EntityModel<Medico>> editMedico(@RequestBody Medico medico) {
        Medico updatedMedico = medicoService.updateMedico(medico);

        EntityModel<Medico> medicoModel = EntityModel.of(updatedMedico);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicoController.class).getMedicoById(updatedMedico.getId())).withSelfRel();
        medicoModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(medicoModel);
    }

    @Operation(summary = "Excluir médico", description = "Exclui um médico com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Médico excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable("id") Long id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
