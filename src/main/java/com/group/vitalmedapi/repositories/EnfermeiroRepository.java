package com.group.vitalmedapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.vitalmedapi.models.Enfermeiro;

public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Long>   {
    
}
