package com.group.vitalmedapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.repositories.FuncionarioRepository;


import com.group.vitalmedapi.models.Funcionario;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public List<Funcionario> findAll(){
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> findById(Long id) {
		return funcionarioRepository.findById(id);
	}

    public Funcionario addFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario updateFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deleteFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
