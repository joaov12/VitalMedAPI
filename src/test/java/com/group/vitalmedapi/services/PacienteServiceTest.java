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

import com.group.vitalmedapi.models.Paciente;
import com.group.vitalmedapi.repositories.PacienteRepository;

class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllSuccess() {
        Paciente paciente = new Paciente();
        when(pacienteRepository.findAll()).thenReturn(List.of(paciente));

        List<Paciente> result = pacienteService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    void testFindAllThrowsExceptionWhenNoPacientes() {
        when(pacienteRepository.findAll()).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pacienteService.findAll();
        });

        assertEquals("Nenhum paciente encontrado.", exception.getMessage());
        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Paciente result = pacienteService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdThrowsExceptionWhenNotFound() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pacienteService.findById(1L);
        });

        assertEquals("Não existe paciente com o ID: 1", exception.getMessage());
        verify(pacienteRepository, times(1)).findById(1L);
    }

    @Test
    void testAddPacienteSuccess() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        Paciente result = pacienteService.addPaciente(paciente);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testUpdatePacienteSuccess() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        when(pacienteRepository.existsById(1L)).thenReturn(true);
        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        Paciente result = pacienteService.updatePaciente(paciente);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(pacienteRepository, times(1)).existsById(1L);
        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    void testUpdatePacienteThrowsExceptionWhenNotFound() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        when(pacienteRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pacienteService.updatePaciente(paciente);
        });

        assertEquals("Paciente não encontrado para o ID: 1", exception.getMessage());
        verify(pacienteRepository, times(1)).existsById(1L);
        verify(pacienteRepository, times(0)).save(paciente);
    }

    @Test
    void testDeletePacienteSuccess() {
        doNothing().when(pacienteRepository).deleteById(1L);

        assertDoesNotThrow(() -> pacienteService.deletePaciente(1L));
        verify(pacienteRepository, times(1)).deleteById(1L);
    }
}
