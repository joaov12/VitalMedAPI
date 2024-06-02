package com.group.vitalmedapi.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "enfermeiros")
public class Enfermeiro extends Funcionario {
    private String coren;

    @ManyToMany(mappedBy = "enfermeiros")
    private List<Cirurgia> cirurgias;

  
    public Enfermeiro() {}
    public Enfermeiro(String nome, Date dataNascimento, Date dataContratacao, double salario, String telefone,
            String endereco, String coren, Departamento departamento) {
        super(nome, dataNascimento, dataContratacao, salario, telefone, endereco, departamento);
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
    public List<Cirurgia> getCirurgias() {
        return cirurgias;
    }
    public void setCirurgias(List<Cirurgia> cirurgias) {
        this.cirurgias = cirurgias;
    }
}
