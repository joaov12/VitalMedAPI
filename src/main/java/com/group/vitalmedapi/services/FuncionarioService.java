package com.group.vitalmedapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.vitalmedapi.repositories.FuncionarioRepository;


import com.group.vitalmedapi.models.Funcionario;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public List<Funcionario> findAll() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        if (funcionarios.isEmpty()) {
            throw new RuntimeException("Nenhum funcionário encontrado.");
        }
        return funcionarios;
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não existe funcionário com o ID: " + id));
    }
}
