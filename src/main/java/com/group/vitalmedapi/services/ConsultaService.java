package com.group.vitalmedapi.services;

import java.util.List;
import java.util.Optional;

import com.group.vitalmedapi.enums.StatusPagamentoEnum;
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
        if(consulta.getStatusProcedimento() == StatusProcedimentoEnum.CONCLUIDO){
            throw new RuntimeException("Uma consulta não pode iniciar com status de CONCLUIDO")
        }

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
                    dto.getStatusProcedimento(), dto.getStatusPagamento());
            return consultaRepository.save(consulta);
        } else {
            throw new RuntimeException("Médico ou Paciente não encontrado");
        }
    }

    public Consulta updateStatusProcedimento(Long id, StatusProcedimentoEnum statusProcedimento) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada para o ID: " + id));

        consulta.setStatusProcedimento(statusProcedimento);

        if (!(consulta.getStatusPagamento() == StatusPagamentoEnum.PAGAMENTO_CONCLUIDO)) {
            throw new RuntimeException("O pagamento dessa consulta ainda não foi feito");
        }

        if (statusProcedimento == StatusProcedimentoEnum.CONCLUIDO) {
            String emailContent = "\nNome Paciente: " + consulta.getPaciente().getNome()
                    + "\nNome Médico: " + consulta.getMedico().getNome()
                    + "\nCRM: " + consulta.getMedico().getCrm()
                    + "\nData agendada: " + consulta.getDataMarcada()
                    + "\nMotivo da consulta: " + consulta.getMotivoDaConsulta();

            emailService.enviarEmail(
                    consulta.getPaciente().getEmail(),
                    "Este é o relatório de sua consulta, " + consulta.getPaciente().getNome(),
                    emailContent);

            emailService.enviarEmail(
                    consulta.getMedico().getEmail(),
                    "Relatório de consulta concluída, paciente: " + consulta.getPaciente().getNome(),
                    emailContent);
        }

        try {
            return consultaRepository.save(consulta);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o status do procedimento", e);
        }
    }

    public Consulta updateStatusPagamento(Long id, StatusPagamentoEnum statusPagamento) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada para o ID: " + id));

        consulta.setStatusPagamento(statusPagamento);

        if (statusPagamento == StatusPagamentoEnum.PAGAMENTO_CONCLUIDO) {

            String emailContent = "\nNome Paciente: " + consulta.getPaciente().getNome()
                    + "\nNome Médico: " + consulta.getMedico().getNome()
                    + "\nCRM: " + consulta.getMedico().getCrm()
                    + "\nData agendada: " + consulta.getDataMarcada()
                    + "\nMotivo da cirurgia: " + consulta.getMotivoDaConsulta();

            // Enviar email para o paciente
            emailService.enviarEmail(
                    consulta.getPaciente().getEmail(),
                    "PAGAMENTO DA CONSULTA CONCLUIDO!!! " + consulta.getPaciente().getNome(),
                    emailContent);
        }

        try {
            return consultaRepository.save(consulta);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o status do pagamento", e);
        }
    }

}
