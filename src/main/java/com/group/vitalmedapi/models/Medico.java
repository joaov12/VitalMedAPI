package com.group.vitalmedapi.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicos")
public class Medico extends Funcionario {
    
    @Column(nullable = false, updatable = false)
    private String crm;

    @JsonIgnore
    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

    @JsonIgnore
    @OneToMany(mappedBy = "medico")
    private List<Cirurgia> cirurgias;

    public Medico(){}
    public Medico(String nome, Date dataNascimento, Date dataContratacao, double salario, String telefone,
            String endereco, String email, String crm, Departamento departamento) {
        super(nome, dataNascimento, dataContratacao, salario, telefone, endereco, email, departamento);
        this.crm = crm;
    }

    public Medico(String crm) {
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
    
}
