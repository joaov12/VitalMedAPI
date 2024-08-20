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

import com.group.vitalmedapi.models.Enfermeiro;
import com.group.vitalmedapi.repositories.EnfermeiroRepository;

class EnfermeiroServiceTest {

    @Mock
    private EnfermeiroRepository enfermeiroRepository;

    @InjectMocks
    private EnfermeiroService enfermeiroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllSuccess() {
        Enfermeiro enfermeiro = new Enfermeiro();
        when(enfermeiroRepository.findAll()).thenReturn(List.of(enfermeiro));

        List<Enfermeiro> result = enfermeiroService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(enfermeiroRepository, times(1)).findAll();
    }

    @Test
    void testFindAllThrowsExceptionWhenNoEnfermeiros() {
        when(enfermeiroRepository.findAll()).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            enfermeiroService.findAll();
        });

        assertEquals("Nenhum enfermeiro encontrado.", exception.getMessage());
        verify(enfermeiroRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        Enfermeiro enfermeiro = new Enfermeiro();
        enfermeiro.setId(1L);
        when(enfermeiroRepository.findById(1L)).thenReturn(Optional.of(enfermeiro));

        Enfermeiro result = enfermeiroService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(enfermeiroRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdThrowsExceptionWhenNotFound() {
        when(enfermeiroRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            enfermeiroService.findById(1L);
        });

        assertEquals("Não existe enfermeiro com o ID: 1", exception.getMessage());
        verify(enfermeiroRepository, times(1)).findById(1L);
    }

    @Test
    void testAddEnfermeiroSuccess() {
        Enfermeiro enfermeiro = new Enfermeiro();
        enfermeiro.setId(1L);
        when(enfermeiroRepository.save(enfermeiro)).thenReturn(enfermeiro);

        Enfermeiro result = enfermeiroService.addEnfermeiro(enfermeiro);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(enfermeiroRepository, times(1)).save(enfermeiro);
    }

    @Test
    void testUpdateEnfermeiroSuccess() {
        Enfermeiro enfermeiro = new Enfermeiro();
        enfermeiro.setId(1L);
        when(enfermeiroRepository.existsById(1L)).thenReturn(true);
        when(enfermeiroRepository.save(enfermeiro)).thenReturn(enfermeiro);

        Enfermeiro result = enfermeiroService.updateEnfermeiro(enfermeiro);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(enfermeiroRepository, times(1)).existsById(1L);
        verify(enfermeiroRepository, times(1)).save(enfermeiro);
    }

    @Test
    void testUpdateEnfermeiroThrowsExceptionWhenNotFound() {
        Enfermeiro enfermeiro = new Enfermeiro();
        enfermeiro.setId(1L);
        when(enfermeiroRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            enfermeiroService.updateEnfermeiro(enfermeiro);
        });

        assertEquals("Enfermeiro não encontrado para o ID: 1", exception.getMessage());
        verify(enfermeiroRepository, times(1)).existsById(1L);
        verify(enfermeiroRepository, times(0)).save(enfermeiro);
    }

    @Test
    void testDeleteEnfermeiroSuccess() {
        doNothing().when(enfermeiroRepository).deleteById(1L);

        assertDoesNotThrow(() -> enfermeiroService.deleteEnfermeiro(1L));
        verify(enfermeiroRepository, times(1)).deleteById(1L);
    }
}
