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

import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.repositories.MedicoRepository;

class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService medicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllSuccess() {
        Medico medico = new Medico();
        when(medicoRepository.findAll()).thenReturn(List.of(medico));

        List<Medico> result = medicoService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(medicoRepository, times(1)).findAll();
    }

    @Test
    void testFindAllThrowsExceptionWhenNoMedicos() {
        when(medicoRepository.findAll()).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            medicoService.findAll();
        });

        assertEquals("Nenhum médico encontrado.", exception.getMessage());
        verify(medicoRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        Medico medico = new Medico();
        medico.setId(1L);
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        Medico result = medicoService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(medicoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdThrowsExceptionWhenNotFound() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            medicoService.findById(1L);
        });

        assertEquals("Não existe médico com o ID: 1", exception.getMessage());
        verify(medicoRepository, times(1)).findById(1L);
    }

    @Test
    void testAddMedicoSuccess() {
        Medico medico = new Medico();
        medico.setId(1L);
        when(medicoRepository.save(medico)).thenReturn(medico);

        Medico result = medicoService.addMedico(medico);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(medicoRepository, times(1)).save(medico);
    }

    @Test
    void testUpdateMedicoSuccess() {
        Medico medico = new Medico();
        medico.setId(1L);
        when(medicoRepository.existsById(1L)).thenReturn(true);
        when(medicoRepository.save(medico)).thenReturn(medico);

        Medico result = medicoService.updateMedico(medico);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(medicoRepository, times(1)).existsById(1L);
        verify(medicoRepository, times(1)).save(medico);
    }

    @Test
    void testUpdateMedicoThrowsExceptionWhenNotFound() {
        Medico medico = new Medico();
        medico.setId(1L);
        when(medicoRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            medicoService.updateMedico(medico);
        });

        assertEquals("Médico não encontrado para o ID: 1", exception.getMessage());
        verify(medicoRepository, times(1)).existsById(1L);
        verify(medicoRepository, times(0)).save(medico);
    }

    @Test
    void testDeleteMedicoSuccess() {
        doNothing().when(medicoRepository).deleteById(1L);

        assertDoesNotThrow(() -> medicoService.deleteMedico(1L));
        verify(medicoRepository, times(1)).deleteById(1L);
    }
}
