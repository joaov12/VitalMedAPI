package com.group.vitalmedapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.models.Enfermeiro;
import com.group.vitalmedapi.repositories.EnfermeiroRepository;

@Service
public class EnfermeiroService {
    
    @Autowired
    private EnfermeiroRepository enfermeiroRepository;


    public List<Enfermeiro> findAll(){
        return enfermeiroRepository.findAll();
    }

    public Optional<Enfermeiro> findById(Long id) {
		return enfermeiroRepository.findById(id);
	}

    public Enfermeiro addEnfermeiro(Enfermeiro enfermeiro) {
        return enfermeiroRepository.save(enfermeiro);
    }

    public Enfermeiro updateEnfermeiro(Enfermeiro enfermeiro) {
        return enfermeiroRepository.save(enfermeiro);
    }

    public void deleteEnfermeiro(Long id) {
        enfermeiroRepository.deleteById(id);
    }
}
