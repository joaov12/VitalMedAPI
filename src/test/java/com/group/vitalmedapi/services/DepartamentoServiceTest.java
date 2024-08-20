package com.group.vitalmedapi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group.vitalmedapi.models.Departamento;
import com.group.vitalmedapi.repositories.DepartamentoRepository;
import com.group.vitalmedapi.services.DepartamentoService;

class DepartamentoServiceTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private DepartamentoService departamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllSuccess() {
        Departamento departamento = new Departamento();
        when(departamentoRepository.findAll()).thenReturn(List.of(departamento));

        List<Departamento> result = departamentoService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(departamentoRepository, times(1)).findAll();
    }

    @Test
    void testFindAllThrowsExceptionWhenNoDepartamentos() {
        when(departamentoRepository.findAll()).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            departamentoService.findAll();
        });

        assertEquals("Nenhum departamento encontrado.", exception.getMessage());
        verify(departamentoRepository, times(1)).findAll();
    }
}
