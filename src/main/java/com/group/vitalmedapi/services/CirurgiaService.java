package com.group.vitalmedapi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.group.vitalmedapi.enums.StatusPagamentoEnum;
import com.group.vitalmedapi.exceptions.CustomizedResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.enums.StatusProcedimentoEnum;
import com.group.vitalmedapi.models.Cirurgia;
import com.group.vitalmedapi.models.Enfermeiro;
import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.models.Paciente;
import com.group.vitalmedapi.models.dtos.CreateCirurgiaDTO;
import com.group.vitalmedapi.repositories.CirurgiaRepository;
import com.group.vitalmedapi.repositories.EnfermeiroRepository;
import com.group.vitalmedapi.repositories.MedicoRepository;
import com.group.vitalmedapi.repositories.PacienteRepository;

@Service
public class CirurgiaService {
    @Autowired
    private CirurgiaRepository cirurgiaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EnfermeiroRepository enfermeiroRepository;

    @Autowired
    private EmailService emailService;

    public List<Cirurgia> findAll() {
        List<Cirurgia> cirurgias = cirurgiaRepository.findAll();
        if (cirurgias.isEmpty()) {
            throw new RuntimeException("Nenhuma cirurgia encontrada.");
        }
        return cirurgias;
    }

    public Cirurgia findById(Long id) {
        return cirurgiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe cirurgia com o ID: " + id));
    }

    public Cirurgia addCirurgia(Cirurgia cirurgia) {
        try {
            return cirurgiaRepository.save(cirurgia);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar cirurgia", e);
        }
    }

    public Cirurgia updateCirurgia(Cirurgia cirurgia) {
        if (!cirurgiaRepository.existsById(cirurgia.getId())) {
            throw new RuntimeException("Cirurgia não encontrada para o ID: " + cirurgia.getId());
        }

        try {
            return cirurgiaRepository.save(cirurgia);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar cirurgia", e);
        }
    }

    public void deleteCirurgia(Long id) {
        cirurgiaRepository.deleteById(id);
    }

    // Cria cirurgias passando apenas os Ids
    public Cirurgia createCirurgia(CreateCirurgiaDTO createCirurgiaDTO) {
        Medico medico = medicoRepository.findById(createCirurgiaDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Medico não encontrado"));
        Paciente paciente = pacienteRepository.findById(createCirurgiaDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        List<Enfermeiro> enfermeiros = createCirurgiaDTO.getEnfermeiroIds().stream()
                .map(id -> enfermeiroRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Enfermeiro não encontrado")))
                .collect(Collectors.toList());

        Cirurgia cirurgia = new Cirurgia(medico, paciente, enfermeiros, createCirurgiaDTO.getDataMarcada(),
                createCirurgiaDTO.getMotivoDaCirurgia(), createCirurgiaDTO.getStatusProcedimento(), createCirurgiaDTO.getStatusPagamento());

        return cirurgiaRepository.save(cirurgia);
    }

    public Cirurgia updateStatusProcedimento(Long id, StatusProcedimentoEnum statusProcedimento) {
        Cirurgia cirurgia = cirurgiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cirurgia não encontrada para o ID: " + id));

        cirurgia.setStatusProcedimento(statusProcedimento);

        if (statusProcedimento == StatusProcedimentoEnum.CONCLUIDO) {
            if(!(cirurgia.getStatusPagamento() == StatusPagamentoEnum.PAGAMENTO_CONCLUIDO)){
                throw new RuntimeException("O pagamento dessa cirurgia ainda não foi feito");
            }

            String emailContent = "\nNome Paciente: " + cirurgia.getPaciente().getNome()
                + "\nNome Médico: " + cirurgia.getMedico().getNome()
                + "\nCRM: " + cirurgia.getMedico().getCrm()
                + "\nData agendada: " + cirurgia.getDataMarcada()
                + "\nMotivo da cirurgia: " + cirurgia.getMotivoDaCirurgia();

            // Enviar email para o paciente
            emailService.enviarEmail(
                cirurgia.getPaciente().getEmail(),
                "Este é o relatório de sua cirurgia, " + cirurgia.getPaciente().getNome(),
                emailContent
            );

            // Enviar email para o médico
            emailService.enviarEmail(
                cirurgia.getMedico().getEmail(),
                "Relatório de cirurgia concluída, paciente: " + cirurgia.getPaciente().getNome(),
                emailContent
            );

            // Enviar email para cada enfermeiro
            for (Enfermeiro enfermeiro : cirurgia.getEnfermeiros()) {
                emailService.enviarEmail(
                    enfermeiro.getEmail(),
                    "Relatório de cirurgia concluída, paciente: " + cirurgia.getPaciente().getNome(),
                    emailContent
                );
            }
        }

        try {
            return cirurgiaRepository.save(cirurgia);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o status do procedimento", e);
        }
    }

    public Cirurgia updateStatusPagamento(Long id, StatusPagamentoEnum statusPagamento) {
        Cirurgia cirurgia = cirurgiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cirurgia não encontrada para o ID: " + id));

        cirurgia.setStatusPagamento(statusPagamento);

        if (statusPagamento == StatusPagamentoEnum.PAGAMENTO_CONCLUIDO){

            String emailContent = "\nNome Paciente: " + cirurgia.getPaciente().getNome()
                    + "\nNome Médico: " + cirurgia.getMedico().getNome()
                    + "\nCRM: " + cirurgia.getMedico().getCrm()
                    + "\nData agendada: " + cirurgia.getDataMarcada()
                    + "\nMotivo da cirurgia: " + cirurgia.getMotivoDaCirurgia();

            // Enviar email para o paciente
            emailService.enviarEmail(
                    cirurgia.getPaciente().getEmail(),
                    "PAGAMENTO DA CIRURGIA CONCLUIDO!!! " + cirurgia.getPaciente().getNome(),
                    emailContent
            );
        }

        try {
            return cirurgiaRepository.save(cirurgia);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o status do pagamento", e);
        }
    }
}
