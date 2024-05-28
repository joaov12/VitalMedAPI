package com.group.vitalmedapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.vitalmedapi.models.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
}
