package com.group.vitalmedapi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group.vitalmedapi.models.Funcionario;
import com.group.vitalmedapi.repositories.FuncionarioRepository;
import com.group.vitalmedapi.services.FuncionarioService;

class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllSuccess() {
        Funcionario funcionario = new Funcionario();
        when(funcionarioRepository.findAll()).thenReturn(List.of(funcionario));

        List<Funcionario> result = funcionarioService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(funcionarioRepository, times(1)).findAll();
    }

    @Test
    void testFindAllThrowsExceptionWhenNoFuncionarios() {
        when(funcionarioRepository.findAll()).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            funcionarioService.findAll();
        });

        assertEquals("Nenhum funcionário encontrado.", exception.getMessage());
        verify(funcionarioRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        Funcionario funcionario = new Funcionario();
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        Funcionario result = funcionarioService.findById(1L);

        assertNotNull(result);
        verify(funcionarioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdThrowsExceptionWhenFuncionarioNotFound() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            funcionarioService.findById(1L);
        });

        assertEquals("Não existe funcionário com o ID: 1", exception.getMessage());
        verify(funcionarioRepository, times(1)).findById(1L);
    }
}
