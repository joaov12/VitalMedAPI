package com.group.vitalmedapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.repositories.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> findAll() {
        List<Medico> medicos = medicoRepository.findAll();
        if (medicos.isEmpty()) {
            throw new RuntimeException("Nenhum médico encontrado.");
        }
        return medicos;
    }

    public Medico findById(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe médico com o ID: " + id));
    }

    public Medico addMedico(Medico medico) {
        try {
            return medicoRepository.save(medico);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar médico", e);
        }
    }

    public Medico updateMedico(Medico medico) {
        if (!medicoRepository.existsById(medico.getId())) {
            throw new RuntimeException("Médico não encontrado para o ID: " + medico.getId());
        }

        try {
            return medicoRepository.save(medico);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar médico", e);
        }
    }

    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
    }
}
