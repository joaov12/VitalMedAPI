package com.group.vitalmedapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.models.Enfermeiro;
import com.group.vitalmedapi.repositories.EnfermeiroRepository;

@Service
public class EnfermeiroService {

    @Autowired
    private EnfermeiroRepository enfermeiroRepository;

    public List<Enfermeiro> findAll() {
        List<Enfermeiro> enfermeiros = enfermeiroRepository.findAll();
        if (enfermeiros.isEmpty()) {
            throw new RuntimeException("Nenhum enfermeiro encontrado.");
        }
        return enfermeiros;
    }

    public Enfermeiro findById(Long id) {
        return enfermeiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe enfermeiro com o ID: " + id));
    }

    public Enfermeiro addEnfermeiro(Enfermeiro enfermeiro) {
        try {
            return enfermeiroRepository.save(enfermeiro);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar enfermeiro", e);
        }
    }

    public Enfermeiro updateEnfermeiro(Enfermeiro enfermeiro) {
        if (!enfermeiroRepository.existsById(enfermeiro.getId())) {
            throw new RuntimeException("Enfermeiro não encontrado para o ID: " + enfermeiro.getId());
        }

        try {
            return enfermeiroRepository.save(enfermeiro);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar enfermeiro", e);
        }
    }

    public void deleteEnfermeiro(Long id) {
        enfermeiroRepository.deleteById(id);
    }
}
