package com.group.vitalmedapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.enums.StatusProcedimentoEnum;
import com.group.vitalmedapi.models.Consulta;
import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.models.Paciente;
import com.group.vitalmedapi.models.dtos.CreateConsultaDTO;
import com.group.vitalmedapi.repositories.ConsultaRepository;
import com.group.vitalmedapi.repositories.MedicoRepository;
import com.group.vitalmedapi.repositories.PacienteRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EmailService emailService;

    public List<Consulta> findAll() {
        List<Consulta> consultas = consultaRepository.findAll();
        if (consultas.isEmpty()) {
            throw new RuntimeException("Nenhuma consulta encontrada.");
        }
        return consultas;
    }

    public Consulta findById(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe consulta com o ID: " + id));
    }

    public Consulta addConsulta(Consulta consulta) {
        try {
            return consultaRepository.save(consulta);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar consulta", e);
        }
    }

    public Consulta updateConsulta(Consulta consulta) {
        if (!consultaRepository.existsById(consulta.getId())) {
            throw new RuntimeException("Consulta não encontrada para o ID: " + consulta.getId());
        }

        try {
            return consultaRepository.save(consulta);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar consulta", e);
        }
    }

    public void deleteConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

    public Consulta createConsulta(CreateConsultaDTO dto) {
        Optional<Medico> medicoOptional = medicoRepository.findById(dto.getMedicoId());
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(dto.getPacienteId());

        if (medicoOptional.isPresent() && pacienteOptional.isPresent()) {
            Medico medico = medicoOptional.get();
            Paciente paciente = pacienteOptional.get();
            Consulta consulta = new Consulta(medico, paciente, dto.getDataHora(), dto.getMotivoDaConsulta(),
                    dto.getStatusProcedimento());
            return consultaRepository.save(consulta);
        } else {
            throw new RuntimeException("Médico ou Paciente não encontrado");
        }
    }

    public Consulta updateStatusProcedimento(Long id, StatusProcedimentoEnum statusProcedimento) {
    Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Consulta não encontrada para o ID: " + id));
    
    consulta.setStatusProcedimento(statusProcedimento);

    if (statusProcedimento == StatusProcedimentoEnum.CONCLUIDO) {
        emailService.enviarEmail(
            consulta.getPaciente().getEmail(),
            "Este é o relatório de sua consulta, " + consulta.getPaciente().getNome(),
            "\nNome Paciente: " + consulta.getPaciente().getNome()
            + "\nNome Médico: " + consulta.getMedico().getNome()
            + "\nCRM: " + consulta.getMedico().getCrm()
            + "\nData agendada: " + consulta.getDataMarcada()
            + "\nMotivo da consulta: " + consulta.getMotivoDaConsulta()
        );
    }

    try {
        return consultaRepository.save(consulta);
    } catch (Exception e) {
        throw new RuntimeException("Erro ao atualizar o status do procedimento", e);
    }
}

}
