package com.group.vitalmedapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.vitalmedapi.models.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
}
