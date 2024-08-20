package com.group.vitalmedapi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group.vitalmedapi.dtos.CreateCirurgiaDTO;
import com.group.vitalmedapi.enums.StatusPagamentoEnum;
import com.group.vitalmedapi.enums.StatusProcedimentoEnum;
import com.group.vitalmedapi.models.Cirurgia;
import com.group.vitalmedapi.models.Enfermeiro;
import com.group.vitalmedapi.models.Medico;
import com.group.vitalmedapi.models.Paciente;
import com.group.vitalmedapi.repositories.CirurgiaRepository;
import com.group.vitalmedapi.repositories.EnfermeiroRepository;
import com.group.vitalmedapi.repositories.MedicoRepository;
import com.group.vitalmedapi.repositories.PacienteRepository;
import com.group.vitalmedapi.services.CirurgiaService;
import com.group.vitalmedapi.services.EmailService;

class CirurgiaServiceTest {

    @Mock
    private CirurgiaRepository cirurgiaRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private EnfermeiroRepository enfermeiroRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private CirurgiaService cirurgiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllSuccess() {
        Cirurgia cirurgia = new Cirurgia();
        when(cirurgiaRepository.findAll()).thenReturn(List.of(cirurgia));

        List<Cirurgia> result = cirurgiaService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(cirurgiaRepository, times(1)).findAll();
    }

    @Test
    void testFindAllThrowsExceptionWhenNoCirurgias() {
        when(cirurgiaRepository.findAll()).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cirurgiaService.findAll();
        });

        assertEquals("Nenhuma cirurgia encontrada.", exception.getMessage());
        verify(cirurgiaRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        Cirurgia cirurgia = new Cirurgia();
        when(cirurgiaRepository.findById(1L)).thenReturn(Optional.of(cirurgia));

        Cirurgia result = cirurgiaService.findById(1L);

        assertNotNull(result);
        verify(cirurgiaRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdThrowsExceptionWhenCirurgiaNotFound() {
        when(cirurgiaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cirurgiaService.findById(1L);
        });

        assertEquals("Não existe cirurgia com o ID: 1", exception.getMessage());
        verify(cirurgiaRepository, times(1)).findById(1L);
    }

    @Test
    void testAddCirurgiaThrowsExceptionWhenStatusConcluido() {
        Cirurgia cirurgia = new Cirurgia();
        cirurgia.setStatusProcedimento(StatusProcedimentoEnum.CONCLUIDO);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cirurgiaService.addCirurgia(cirurgia);
        });

        assertEquals("Uma cirurgia não pode iniciar com status de CONCLUIDO", exception.getMessage());
        verify(cirurgiaRepository, never()).save(any(Cirurgia.class));
    }

    @Test
    void testCreateCirurgiaSuccess() {
        // Simulação de criação de cirurgia
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        CreateCirurgiaDTO dto = new CreateCirurgiaDTO();
        dto.setMedicoId(1L);
        dto.setPacienteId(1L);
        dto.setEnfermeiroIds(List.of(1L, 2L));
        dto.setDataMarcada(date);
        dto.setMotivoDaCirurgia("Teste");
        dto.setStatusProcedimento(StatusProcedimentoEnum.A_FAZER);
        dto.setStatusPagamento(StatusPagamentoEnum.PAGAMENTO_PENDENTE);

        Medico medico = new Medico();
        Paciente paciente = new Paciente();
        Enfermeiro enfermeiro1 = new Enfermeiro();
        Enfermeiro enfermeiro2 = new Enfermeiro();

        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(enfermeiroRepository.findById(1L)).thenReturn(Optional.of(enfermeiro1));
        when(enfermeiroRepository.findById(2L)).thenReturn(Optional.of(enfermeiro2));


        Cirurgia result = cirurgiaService.createCirurgia(dto);

        assertNull(result);
        verify(cirurgiaRepository, times(1)).save(any(Cirurgia.class));
    }

}
