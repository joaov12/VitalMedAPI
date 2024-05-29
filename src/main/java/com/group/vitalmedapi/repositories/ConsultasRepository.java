package com.group.vitalmedapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.vitalmedapi.models.Consulta;

public interface ConsultasRepository extends JpaRepository<Consulta, Long> {
    
}
