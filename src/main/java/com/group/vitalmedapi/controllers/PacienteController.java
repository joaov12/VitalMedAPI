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

import com.group.vitalmedapi.models.Paciente;
import com.group.vitalmedapi.services.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @Operation(summary = "Obter todos os pacientes", description = "Retorna uma lista de todos os pacientes cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<EntityModel<Paciente>>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();

        List<EntityModel<Paciente>> pacienteModels = pacientes.stream()
                .map(paciente -> {
                    EntityModel<Paciente> pacienteModel = EntityModel.of(paciente);
                    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PacienteController.class).getPacienteById(paciente.getId())).withSelfRel();
                    pacienteModel.add(selfLink);
                    return pacienteModel;
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(pacienteModels);
    }

    @Operation(summary = "Obter paciente por ID", description = "Retorna um paciente com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<EntityModel<Paciente>> getPacienteById(@PathVariable("id") Long id) {
        Paciente paciente = pacienteService.findById(id);

        EntityModel<Paciente> pacienteModel = EntityModel.of(paciente);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PacienteController.class).getPacienteById(id)).withSelfRel();
        pacienteModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(pacienteModel);
    }

    @Operation(summary = "Adicionar paciente", description = "Adiciona um novo paciente")
    @PostMapping("/add")
    public ResponseEntity<EntityModel<Paciente>> addPaciente(@RequestBody Paciente paciente) {
        Paciente createdPaciente = pacienteService.addPaciente(paciente);

        EntityModel<Paciente> pacienteModel = EntityModel.of(createdPaciente);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PacienteController.class).getPacienteById(createdPaciente.getId())).withSelfRel();
        pacienteModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteModel);
    }

    @Operation(summary = "Editar paciente", description = "Edita um paciente existente")
    @PutMapping("/edit")
    public ResponseEntity<EntityModel<Paciente>> editPaciente(@RequestBody Paciente paciente) {
        Paciente updatedPaciente = pacienteService.updatePaciente(paciente);

        EntityModel<Paciente> pacienteModel = EntityModel.of(updatedPaciente);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PacienteController.class).getPacienteById(updatedPaciente.getId())).withSelfRel();
        pacienteModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(pacienteModel);
    }

    @Operation(summary = "Excluir paciente", description = "Exclui um paciente com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable("id") Long id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
