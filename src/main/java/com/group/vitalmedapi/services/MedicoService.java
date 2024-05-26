package com.group.vitalmedapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.repositories.MedicoRepository;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> findAll(){
        return medicoRepository.findAll();
    }

    public Optional<Medico> findById(Long id) {
		return medicoRepository.findById(id);
	}

    public Medico addMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico updateMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
    }
}
