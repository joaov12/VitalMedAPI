package com.group.vitalmedapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.models.Departamento;
import com.group.vitalmedapi.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {
    
    @Autowired
    DepartamentoRepository departamentoRepository;

    public List<Departamento> findAll(){
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> findById(Long id) {
		return departamentoRepository.findById(id);
	}

    public Departamento addDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Departamento updateDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public void deleteDepartamento(Long id) {
        departamentoRepository.deleteById(id);
    }
}
