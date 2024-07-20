package com.group.vitalmedapi.models.dtos;

import java.util.Date;
import java.util.List;

import com.group.vitalmedapi.enums.StatusPagamentoEnum;
import com.group.vitalmedapi.enums.StatusProcedimentoEnum;

public class CreateCirurgiaDTO {
    private Long medicoId;
    private Long pacienteId;
    private List<Long> enfermeiroIds;
    private Date dataMarcada;
    private String motivoDaCirurgia;
    private StatusProcedimentoEnum statusProcedimento;
    private StatusPagamentoEnum statusPagamento;

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public List<Long> getEnfermeiroIds() {
        return enfermeiroIds;
    }

    public void setEnfermeiroIds(List<Long> enfermeiroIds) {
        this.enfermeiroIds = enfermeiroIds;
    }

    public Date getDataMarcada() {
        return dataMarcada;
    }

    public void setDataMarcada(Date dataMarcada) {
        this.dataMarcada = dataMarcada;
    }

    public String getMotivoDaCirurgia() {
        return motivoDaCirurgia;
    }

    public void setMotivoDaCirurgia(String motivoDaCirurgia) {
        this.motivoDaCirurgia = motivoDaCirurgia;
    }

    public StatusProcedimentoEnum getStatusProcedimento() {
        return statusProcedimento;
    }

    public void setStatusProcedimento(StatusProcedimentoEnum statusProcedimento) {
        this.statusProcedimento = statusProcedimento;
    }

    public StatusPagamentoEnum getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamentoEnum statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
