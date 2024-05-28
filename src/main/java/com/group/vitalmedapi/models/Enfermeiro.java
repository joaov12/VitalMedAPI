package com.group.vitalmedapi.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "enfermeiros")
public class Enfermeiro extends Funcionario {
    private String coren;

    public Enfermeiro() {}
    public Enfermeiro(String nome, Date dataNascimento, Date dataContratacao, double salario, String telefone,
            String endereco, String coren) {
        super(nome, dataNascimento, dataContratacao, salario, telefone, endereco);
        this.coren = coren;
    }

    public Enfermeiro(String coren) {
        this.coren = coren;
    }

    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }
}
