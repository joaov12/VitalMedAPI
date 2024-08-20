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

import com.group.vitalmedapi.dtos.CreateConsultaDTO;
import com.group.vitalmedapi.enums.StatusPagamentoEnum;
import com.group.vitalmedapi.enums.StatusProcedimentoEnum;
import com.group.vitalmedapi.models.Consulta;
import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.models.Paciente;
import com.group.vitalmedapi.repositories.ConsultaRepository;
import com.group.vitalmedapi.repositories.MedicoRepository;
import com.group.vitalmedapi.repositories.PacienteRepository;

class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ConsultaService consultaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllSuccess() {
        // Simulação (Mock) do repositório retornando uma lista com uma consulta
        Consulta consulta = new Consulta();
        when(consultaRepository.findAll()).thenReturn(List.of(consulta));

        List<Consulta> result = consultaService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(consultaRepository, times(1)).findAll();
    }

    @Test
    void testFindAllThrowsExceptionWhenNoConsultas() {
        when(consultaRepository.findAll()).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.findAll();
        });

        assertEquals("Nenhuma consulta encontrada.", exception.getMessage());
        verify(consultaRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        Consulta consulta = new Consulta();
        consulta.setId(1L);
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        Consulta result = consultaService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdThrowsExceptionWhenNotFound() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.findById(1L);
        });

        assertEquals("Não existe consulta com o ID: 1", exception.getMessage());
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    void testAddConsultaSuccess() {
        Consulta consulta = new Consulta();
        consulta.setStatusProcedimento(StatusProcedimentoEnum.A_FAZER);
        when(consultaRepository.save(consulta)).thenReturn(consulta);

        Consulta result = consultaService.addConsulta(consulta);

        assertNotNull(result);
        assertEquals(StatusProcedimentoEnum.A_FAZER, result.getStatusProcedimento());
        verify(consultaRepository, times(1)).save(consulta);
    }

    @Test
    void testAddConsultaThrowsExceptionWhenStatusIsConcluido() {
        Consulta consulta = new Consulta();
        consulta.setStatusProcedimento(StatusProcedimentoEnum.CONCLUIDO);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.addConsulta(consulta);
        });

        assertEquals("Uma consulta não pode iniciar com status de CONCLUIDO", exception.getMessage());
        verify(consultaRepository, times(0)).save(consulta);
    }

    @Test
    void testUpdateConsultaSuccess() {
        Consulta consulta = new Consulta();
        consulta.setId(1L);
        when(consultaRepository.existsById(1L)).thenReturn(true);
        when(consultaRepository.save(consulta)).thenReturn(consulta);

        Consulta result = consultaService.updateConsulta(consulta);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(consultaRepository, times(1)).existsById(1L);
        verify(consultaRepository, times(1)).save(consulta);
    }

    @Test
    void testUpdateConsultaThrowsExceptionWhenNotFound() {
        Consulta consulta = new Consulta();
        consulta.setId(1L);
        when(consultaRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.updateConsulta(consulta);
        });

        assertEquals("Consulta não encontrada para o ID: 1", exception.getMessage());
        verify(consultaRepository, times(1)).existsById(1L);
        verify(consultaRepository, times(0)).save(consulta);
    }

    @Test
    void testDeleteConsultaSuccess() {
        doNothing().when(consultaRepository).deleteById(1L);

        assertDoesNotThrow(() -> consultaService.deleteConsulta(1L));
        verify(consultaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCreateConsultaSuccess() {
        CreateConsultaDTO dto = new CreateConsultaDTO();
        dto.setMedicoId(1L);
        dto.setPacienteId(1L);
        dto.setDataHora(null);
        dto.setMotivoDaConsulta("Motivo");
        dto.setStatusProcedimento(StatusProcedimentoEnum.A_FAZER);
        dto.setStatusPagamento(StatusPagamentoEnum.PAGAMENTO_PENDENTE);

        Medico medico = new Medico();
        Paciente paciente = new Paciente();

        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(consultaRepository.save(any(Consulta.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        Consulta result = consultaService.createConsulta(dto);

        assertNotNull(result);
        assertEquals(medico, result.getMedico());
        assertEquals(paciente, result.getPaciente());
        verify(medicoRepository, times(1)).findById(1L);
        verify(pacienteRepository, times(1)).findById(1L);
        verify(consultaRepository, times(1)).save(any(Consulta.class));
    }

    @Test
    void testCreateConsultaThrowsExceptionWhenMedicoOrPacienteNotFound() {
        CreateConsultaDTO dto = new CreateConsultaDTO();
        dto.setMedicoId(1L);
        dto.setPacienteId(1L);

        when(medicoRepository.findById(1L)).thenReturn(Optional.empty());
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(new Paciente()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.createConsulta(dto);
        });

        assertEquals("Médico ou Paciente não encontrado", exception.getMessage());
        verify(medicoRepository, times(1)).findById(1L);
        verify(pacienteRepository, times(1)).findById(1L);
        verify(consultaRepository, times(0)).save(any(Consulta.class));
    }


    @Test
    void testUpdateStatusProcedimentoThrowsExceptionWhenStatusPagamentoNotConcluido() {
        Consulta consulta = new Consulta();
        consulta.setId(1L);
        consulta.setStatusPagamento(StatusPagamentoEnum.PAGAMENTO_PENDENTE);
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            consultaService.updateStatusProcedimento(1L, StatusProcedimentoEnum.CONCLUIDO);
        });

        assertEquals("O pagamento dessa consulta ainda não foi feito", exception.getMessage());
        verify(consultaRepository, times(1)).findById(1L);
        verify(consultaRepository, times(0)).save(consulta);
        verify(emailService, times(0)).enviarEmail(anyString(), anyString(), anyString());
    }

}
