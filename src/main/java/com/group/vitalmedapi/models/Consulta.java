package com.group.vitalmedapi.models;

import java.util.Date;

import com.group.vitalmedapi.enums.StatusPagamentoEnum;
import com.group.vitalmedapi.enums.StatusProcedimentoEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;

    private Date dataMarcada;
    private String motivoDaConsulta;

    private StatusProcedimentoEnum statusProcedimento;
    private StatusPagamentoEnum statusPagamento;

    public Consulta(){}
    public Consulta(Medico medico, Paciente paciente, Date dataMarcada, String motivoDaConsulta,
            StatusProcedimentoEnum statusProcedimento, StatusPagamentoEnum statusPagamento) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataMarcada = dataMarcada;
        this.motivoDaConsulta = motivoDaConsulta;
        this.statusProcedimento = statusProcedimento;
        this.statusPagamento = statusPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getDataMarcada() {
        return dataMarcada;
    }

    public void setDataMarcada(Date dataMarcada) {
        this.dataMarcada = dataMarcada;
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
