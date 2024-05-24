package com.group.vitalmedapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.vitalmedapi.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    
}
