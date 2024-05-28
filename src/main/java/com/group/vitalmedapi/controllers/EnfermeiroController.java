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

import com.group.vitalmedapi.models.Enfermeiro;
import com.group.vitalmedapi.services.EnfermeiroService;

@RestController
@RequestMapping("/enfermeiros")
public class EnfermeiroController {
    @Autowired
    EnfermeiroService enfermeiroService;

    @GetMapping("/all")
    public ResponseEntity<List<Enfermeiro>> getAllEnfermeiros() {
        return ResponseEntity.status(HttpStatus.OK).body(enfermeiroService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Enfermeiro>> getEnfermeiroById(@PathVariable("id") Long id) {
       return ResponseEntity.status(HttpStatus.OK).body(enfermeiroService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Enfermeiro> addEnfermeiro(@RequestBody Enfermeiro enfermeiro) {
       return ResponseEntity.status(HttpStatus.CREATED).body(enfermeiroService.addEnfermeiro(enfermeiro));
    }

    @PutMapping("/edit")
    public ResponseEntity<Enfermeiro> editEnfermeiro(@RequestBody Enfermeiro enfermeiro) {
       return ResponseEntity.status(HttpStatus.OK).body(enfermeiroService.updateEnfermeiro(enfermeiro));
    }

        
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteEnfermeiro(@PathVariable("id") Long id) {
      enfermeiroService.deleteEnfermeiro(id); 
      return ResponseEntity.status(HttpStatus.OK).body("Enfermeiro deletado com sucesso"); // Todo: Mudar a mensagem, deixar uma padrão
   }
}