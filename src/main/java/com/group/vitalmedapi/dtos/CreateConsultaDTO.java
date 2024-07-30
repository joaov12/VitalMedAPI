package com.group.vitalmedapi.dtos;

import java.util.Date;

import com.group.vitalmedapi.enums.StatusPagamentoEnum;
import com.group.vitalmedapi.enums.StatusProcedimentoEnum;

public class CreateConsultaDTO {
    private Date dataHora;
    private Long medicoId;
    private Long pacienteId;
    private String motivoDaConsulta;
    private StatusProcedimentoEnum statusProcedimento;
    private StatusPagamentoEnum statusPagamento;

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

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

    public String getMotivoDaConsulta() {
        return motivoDaConsulta;
    }

    public void setMotivoDaConsulta(String motivoDaConsulta) {
        this.motivoDaConsulta = motivoDaConsulta;
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
