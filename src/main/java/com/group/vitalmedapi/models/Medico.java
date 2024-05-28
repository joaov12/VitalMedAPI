package com.group.vitalmedapi.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicos")
public class Medico extends Funcionario {
    
    @Column(nullable = false, updatable = false)
    private String crm;


    public Medico(){}
    public Medico(String nome, Date dataNascimento, Date dataContratacao, double salario, String telefone,
            String endereco, String crm, Departamento departamento) {
        super(nome, dataNascimento, dataContratacao, salario, telefone, endereco, departamento);
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

    
}
