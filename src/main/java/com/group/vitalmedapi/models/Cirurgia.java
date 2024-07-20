package com.group.vitalmedapi.models;

import java.util.Date;
import java.util.List;

import com.group.vitalmedapi.enums.StatusPagamentoEnum;
import com.group.vitalmedapi.enums.StatusProcedimentoEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "cirurgias")
public class Cirurgia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
    @ManyToMany
    private List<Enfermeiro> enfermeiros;

    private Date dataMarcada;
    private String motivoDaCirurgia;
    private StatusProcedimentoEnum statusProcedimento;
    private StatusPagamentoEnum statusPagamento;

    public Cirurgia(){}
    public Cirurgia(Medico medico, Paciente paciente, List<Enfermeiro> enfermeiros, Date dataMarcada,
            String motivoDaCirurgia, StatusProcedimentoEnum statusProcedimento, StatusPagamentoEnum statusPagamento) {
        this.medico = medico;
        this.paciente = paciente;
        this.enfermeiros = enfermeiros;
        this.dataMarcada = dataMarcada;
        this.motivoDaCirurgia = motivoDaCirurgia;
        this.statusProcedimento = statusProcedimento;
        this.statusPagamento = statusPagamento;
    }
    
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
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
    public List<Enfermeiro> getEnfermeiros() {
        return enfermeiros;
    }
    public void setEnfermeiros(List<Enfermeiro> enfermeiros) {
        this.enfermeiros = enfermeiros;
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
