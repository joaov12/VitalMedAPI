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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("consultas")
@Tag(name = "Consultas")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @Operation(summary = "Obter todas as Consultas", description = "Retorna uma lista de todas as consultas cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<EntityModel<Consulta>> getConsultaById(@PathVariable("id") Long id) {
        Consulta consulta = consultaService.findById(id);

        EntityModel<Consulta> consultaModel = EntityModel.of(consulta);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(id)).withSelfRel();
        consultaModel.add(selfLink);

        Link updateStatusProcedimentoLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).updateStatusProcedimento(id, null)).withRel("updateStatusProcedimento");
        consultaModel.add(updateStatusProcedimentoLink);

        Link updateStatusPagamentoLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).updateStatusPagamento(id, null)).withRel("updateStatusPagamento");
        consultaModel.add(updateStatusPagamentoLink);

        return ResponseEntity.status(HttpStatus.OK).body(consultaModel);
    }

    @Operation(summary = "Adicionar consulta", description = "Adiciona uma nova consulta")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Consulta adicionada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/add")
    public ResponseEntity<EntityModel<Consulta>> addConsulta(@RequestBody Consulta consulta) {
        Consulta novaConsulta = consultaService.addConsulta(consulta);

        EntityModel<Consulta> consultaModel = EntityModel.of(novaConsulta);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(novaConsulta.getId())).withSelfRel();
        consultaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(consultaModel);
    }

    @Operation(summary = "Editar consulta", description = "Edita uma consulta existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta editada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/edit")
    public ResponseEntity<EntityModel<Consulta>> editConsulta(@RequestBody Consulta consulta) {
        Consulta consultaAtualizada = consultaService.updateConsulta(consulta);

        EntityModel<Consulta> consultaModel = EntityModel.of(consultaAtualizada);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(consultaAtualizada.getId())).withSelfRel();
        consultaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(consultaModel);
    }

    @Operation(summary = "Excluir consulta", description = "Exclui uma consulta com base no ID fornecido")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Consulta excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteConsulta(@PathVariable("id") Long id) {
        consultaService.deleteConsulta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Adicionar uma consulta usando IDs", description = "Cria consulta passando apenas ID do médico e paciente + informações necessárias")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Consulta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/createWID")
    public ResponseEntity<EntityModel<Consulta>> createConsulta(@RequestBody CreateConsultaDTO dto) {
        Consulta consulta = consultaService.createConsulta(dto);

        EntityModel<Consulta> consultaModel = EntityModel.of(consulta);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultaController.class).getConsultaById(consulta.getId())).withSelfRel();
        consultaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(consultaModel);
    }

    @Operation(summary = "Atualizar status do procedimento", description = "Atualiza apenas o status do procedimento de uma consulta existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status do procedimento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
            @ApiResponse(responseCode = "400", description = "Status do procedimento inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status do pagamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
            @ApiResponse(responseCode = "400", description = "Status de pagamento inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
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
