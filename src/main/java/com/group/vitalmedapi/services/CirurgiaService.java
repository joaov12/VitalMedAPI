package com.group.vitalmedapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.models.Cirurgia;
import com.group.vitalmedapi.repositories.CirurgiaRepository;

@Service
public class CirurgiaService {
    @Autowired
    private CirurgiaRepository cirurgiaRepository;
    
    public List<Cirurgia> findAll(){
        return cirurgiaRepository.findAll();
    }

    public Optional<Cirurgia> findById(Long id) {
		return cirurgiaRepository.findById(id);
	}

    public Cirurgia addCirurgia(Cirurgia cirurgia) {
        return cirurgiaRepository.save(cirurgia);
    }

    public Cirurgia updateCirurgia(Cirurgia cirurgia) {
        return cirurgiaRepository.save(cirurgia);
    }

    public void deleteCirurgia(Long id) {
        cirurgiaRepository.deleteById(id);
    }
}
