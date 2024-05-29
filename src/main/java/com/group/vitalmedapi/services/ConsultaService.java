package com.group.vitalmedapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Consulta> findAll(){
        return consultaRepository.findAll();
    }

    public Optional<Consulta> findById(Long id) {
		return consultaRepository.findById(id);
	}

    public Consulta addConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Consulta updateConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
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
            Consulta consulta = new Consulta(medico, paciente, dto.getDataHora(), dto.getMotivoDaConsulta(), dto.getStatusProcedimento());
            return consultaRepository.save(consulta);
        } else {
            throw new RuntimeException("Médico ou Paciente não encontrado");
        }
    }
}
