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

import com.group.vitalmedapi.dtos.CreateConsultaDTO;
import com.group.vitalmedapi.enums.StatusPagamentoEnum;
import com.group.vitalmedapi.enums.StatusProcedimentoEnum;
import com.group.vitalmedapi.models.Consulta;
import com.group.vitalmedapi.services.ConsultaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("consultas")
@Tag(name = "Consulta")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @Operation(summary = "Obter todas as Consultas", description = "Retorna uma lista de todas as consultas cadastradas")
    @GetMapping("/all")
    public ResponseEntity<List<EntityModel<Consulta>>> getAllConsultas() {
        List<Consulta> consultas = consultaService.findAll();

        List<EntityModel<Consulta>> consultaModels = consultas.stream()
                .map(consulta -> {
                    EntityModel<Consulta> consultaModel = EntityModel.of(consulta);
                    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(consulta.getId())).withSelfRel();
                    consultaModel.add(selfLink);
                    return consultaModel;
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(consultaModels);
    }

    @Operation(summary = "Obter consulta por ID", description = "Retorna uma consulta com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<EntityModel<Consulta>> getConsultaById(@PathVariable("id") Long id) {
        Consulta consulta = consultaService.findById(id);

        EntityModel<Consulta> consultaModel = EntityModel.of(consulta);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(id)).withSelfRel();
        consultaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(consultaModel);
    }

    @Operation(summary = "Adicionar consulta", description = "Adiciona uma nova consulta")
    @PostMapping("/add")
    public ResponseEntity<EntityModel<Consulta>> addConsulta(@RequestBody Consulta consulta) {
        Consulta novaConsulta = consultaService.addConsulta(consulta);

        EntityModel<Consulta> consultaModel = EntityModel.of(novaConsulta);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(novaConsulta.getId())).withSelfRel();
        consultaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(consultaModel);
    }

    @Operation(summary = "Editar consulta", description = "Edita uma consulta existente")
    @PutMapping("/edit")
    public ResponseEntity<EntityModel<Consulta>> editConsulta(@RequestBody Consulta consulta) {
        Consulta consultaAtualizada = consultaService.updateConsulta(consulta);

        EntityModel<Consulta> consultaModel = EntityModel.of(consultaAtualizada);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(consultaAtualizada.getId())).withSelfRel();
        consultaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(consultaModel);
    }

    @Operation(summary = "Excluir consulta", description = "Exclui uma consulta com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteConsulta(@PathVariable("id") Long id) {
        consultaService.deleteConsulta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Adicionar uma consulta usando IDs", description = "Cria consulta passando apenas ID do médico e paciente + informações necessárias")
    @PostMapping("/createWID")
    public ResponseEntity<EntityModel<Consulta>> createConsulta(@RequestBody CreateConsultaDTO dto) {
        Consulta consulta = consultaService.createConsulta(dto);

        EntityModel<Consulta> consultaModel = EntityModel.of(consulta);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(consulta.getId())).withSelfRel();
        consultaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(consultaModel);
    }

    @Operation(summary = "Atualizar status do procedimento", description = "Atualiza apenas o status do procedimento de uma consulta existente")
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<EntityModel<Consulta>> updateStatusProcedimento(@PathVariable("id") Long id,
                                                                          @RequestBody StatusProcedimentoEnum statusProcedimento) {
        Consulta consultaAtualizada = consultaService.updateStatusProcedimento(id, statusProcedimento);

        EntityModel<Consulta> consultaModel = EntityModel.of(consultaAtualizada);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(consultaAtualizada.getId())).withSelfRel();
        consultaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(consultaModel);
    }

    @Operation(summary = "Atualizar status do pagamento", description = "Atualiza apenas o status do pagamento de uma consulta existente")
    @PutMapping("/updateStatusPagamento/{id}")
    public ResponseEntity<EntityModel<Consulta>> updateStatusPagamento(@PathVariable("id") Long id,
                                                                       @RequestBody StatusPagamentoEnum statusPagamento) {
        Consulta consultaAtualizada = consultaService.updateStatusPagamento(id, statusPagamento);

        EntityModel<Consulta> consultaModel = EntityModel.of(consultaAtualizada);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(consultaAtualizada.getId())).withSelfRel();
        consultaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(consultaModel);
    }
}
