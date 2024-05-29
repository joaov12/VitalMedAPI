package com.group.vitalmedapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.vitalmedapi.models.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    
}
