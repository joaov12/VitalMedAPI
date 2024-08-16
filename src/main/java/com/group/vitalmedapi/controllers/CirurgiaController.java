package com.group.vitalmedapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.group.vitalmedapi.enums.StatusPagamentoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group.vitalmedapi.enums.StatusProcedimentoEnum;
import com.group.vitalmedapi.models.Cirurgia;
import com.group.vitalmedapi.dtos.CreateCirurgiaDTO;
import com.group.vitalmedapi.services.CirurgiaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("cirurgias")
@Tag(name = "Cirurgia")
public class CirurgiaController {

    @Autowired
    CirurgiaService cirurgiaService;

    @Operation(summary = "Obter todas as Cirurgias", description = "Retorna uma lista de todas as cirurgias cadastrados")
    @GetMapping("/all")
    public ResponseEntity<List<EntityModel<Cirurgia>>> getAllCirurgias() {
        List<Cirurgia> cirurgias = cirurgiaService.findAll();

        List<EntityModel<Cirurgia>> cirurgiaModels = cirurgias.stream()
                .map(cirurgia -> {
                    EntityModel<Cirurgia> cirurgiaModel = EntityModel.of(cirurgia);
                    Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CirurgiaController.class).getCirurgiaById(cirurgia.getId())).withSelfRel();
                    cirurgiaModel.add(selfLink);
                    return cirurgiaModel;
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(cirurgiaModels);
    }

    @Operation(summary = "Obter cirurgia por ID", description = "Retorna uma cirurgia com base no ID fornecido")
    @GetMapping("/find/{id}")
    public ResponseEntity<EntityModel<Cirurgia>> getCirurgiaById(@PathVariable("id") Long id) {
        Cirurgia cirurgia = cirurgiaService.findById(id);

        EntityModel<Cirurgia> cirurgiaModel = EntityModel.of(cirurgia);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CirurgiaController.class).getCirurgiaById(id)).withSelfRel();
        cirurgiaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(cirurgiaModel);
    }

    @Operation(summary = "Adicionar cirurgia", description = "Adiciona uma nova cirurgia")
    @PostMapping("/add")
    public ResponseEntity<EntityModel<Cirurgia>> addCirurgia(@RequestBody Cirurgia cirurgia) {
        Cirurgia novaCirurgia = cirurgiaService.addCirurgia(cirurgia);

        EntityModel<Cirurgia> cirurgiaModel = EntityModel.of(novaCirurgia);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CirurgiaController.class).getCirurgiaById(novaCirurgia.getId())).withSelfRel();
        cirurgiaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(cirurgiaModel);
    }

    @Operation(summary = "Editar cirurgia", description = "Edita uma cirurgia existente")
    @PutMapping("/edit")
    public ResponseEntity<EntityModel<Cirurgia>> editCirurgia(@RequestBody Cirurgia cirurgia) {
        Cirurgia cirurgiaAtualizada = cirurgiaService.updateCirurgia(cirurgia);

        EntityModel<Cirurgia> cirurgiaModel = EntityModel.of(cirurgiaAtualizada);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CirurgiaController.class).getCirurgiaById(cirurgiaAtualizada.getId())).withSelfRel();
        cirurgiaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(cirurgiaModel);
    }

    @Operation(summary = "Excluir cirurgia", description = "Exclui uma cirurgia com base no ID fornecido")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCirurgia(@PathVariable("id") Long id) {
        cirurgiaService.deleteCirurgia(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cirurgia deletada com sucesso");
    }

    @Operation(summary = "Adicionar uma cirurgia usando IDs", description = "Cria cirurgia passando apenas IDs (Médico, Paciente, Lista de ID de enfermeiros) + informações necessárias")
    @PostMapping("/createWID")
    public EntityModel<Cirurgia> criarCirurgia(@RequestBody CreateCirurgiaDTO createCirurgiaDTO) {
        Cirurgia novaCirurgia = cirurgiaService.createCirurgia(createCirurgiaDTO);

        EntityModel<Cirurgia> cirurgiaModel = EntityModel.of(novaCirurgia);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CirurgiaController.class).getCirurgiaById(novaCirurgia.getId())).withSelfRel();
        cirurgiaModel.add(selfLink);

        return cirurgiaModel;
    }

    @Operation(summary = "Atualizar status do procedimento", description = "Atualiza apenas o status do procedimento de uma cirurgia existente")
    @PutMapping("/updateStatusProcedimento/{id}")
    public ResponseEntity<EntityModel<Cirurgia>> updateStatusProcedimento(@PathVariable("id") Long id, @RequestBody StatusProcedimentoEnum statusProcedimento) {
        Cirurgia cirurgiaAtualizada = cirurgiaService.updateStatusProcedimento(id, statusProcedimento);

        EntityModel<Cirurgia> cirurgiaModel = EntityModel.of(cirurgiaAtualizada);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CirurgiaController.class).getCirurgiaById(cirurgiaAtualizada.getId())).withSelfRel();
        cirurgiaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(cirurgiaModel);
    }

    @Operation(summary = "Atualizar status do pagamento", description = "Atualiza apenas o status do pagamento de uma cirurgia existente")
    @PutMapping("/updateStatusPagamento/{id}")
    public ResponseEntity<EntityModel<Cirurgia>> updateStatusPagamento(@PathVariable("id") Long id, @RequestBody StatusPagamentoEnum statusPagamento) {
        Cirurgia cirurgiaAtualizada = cirurgiaService.updateStatusPagamento(id, statusPagamento);

        EntityModel<Cirurgia> cirurgiaModel = EntityModel.of(cirurgiaAtualizada);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CirurgiaController.class).getCirurgiaById(cirurgiaAtualizada.getId())).withSelfRel();
        cirurgiaModel.add(selfLink);

        return ResponseEntity.status(HttpStatus.OK).body(cirurgiaModel);
    }
}
