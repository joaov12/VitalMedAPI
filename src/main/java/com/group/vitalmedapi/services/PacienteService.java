package com.group.vitalmedapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.models.Paciente;
import com.group.vitalmedapi.repositories.PacienteRepository;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        if (pacientes.isEmpty()) {
            throw new RuntimeException("Nenhum paciente encontrado.");
        }
        return pacientes;
    }

    public Paciente findById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe paciente com o ID: " + id));
    }

    public Paciente addPaciente(Paciente paciente) {
        try {
            return pacienteRepository.save(paciente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar paciente", e);
        }
    }

    public Paciente updatePaciente(Paciente paciente) {
        if (!pacienteRepository.existsById(paciente.getId())) {
            throw new RuntimeException("Paciente não encontrado para o ID: " + paciente.getId());
        }

        try {
            return pacienteRepository.save(paciente);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar paciente", e);
        }
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
