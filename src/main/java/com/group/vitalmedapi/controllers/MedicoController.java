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

import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.services.MedicoService;

@RestController
@RequestMapping("/funcionarios")
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @GetMapping("/all")
    public ResponseEntity<List<Medico>> getAllMedicos() {
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Medico>> getMedicoById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(medicoService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Medico> addMedico(@RequestBody Medico medico) {
       return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.addMedico(medico));
    }

    @PutMapping("/edit")
    public ResponseEntity<Medico> editMedico(@RequestBody Medico medico) {
       return ResponseEntity.status(HttpStatus.OK).body(medicoService.updateMedico(medico));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable("id") Long id) {
       medicoService.deleteMedico(id); 
       return ResponseEntity.status(HttpStatus.OK).body("Medico deletado com sucesso");
    }

}
