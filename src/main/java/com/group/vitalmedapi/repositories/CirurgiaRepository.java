package com.group.vitalmedapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.vitalmedapi.models.Cirurgia;

public interface CirurgiaRepository extends JpaRepository<Cirurgia, Long> {
    
}
