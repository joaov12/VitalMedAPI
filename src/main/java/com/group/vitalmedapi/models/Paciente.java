package com.group.vitalmedapi.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Nome;
    private Date DataNascimento;
    private String Telefone;
    private String Endereco;
    private String ObservacoesPaciente;

    public Paciente(){}
    public Paciente(String nome, Date dataNascimento, String telefone, String endereco, String observacoesPaciente) {
        Nome = nome;
        DataNascimento = dataNascimento;
        Telefone = telefone;
        Endereco = endereco;
        ObservacoesPaciente = observacoesPaciente;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public Date getDataNascimento() {
        return DataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        DataNascimento = dataNascimento;
    }
    public String getTelefone() {
        return Telefone;
    }
    public void setTelefone(String telefone) {
        Telefone = telefone;
    }
    public String getEndereco() {
        return Endereco;
    }
    public void setEndereco(String endereco) {
        Endereco = endereco;
    }
    public String getObservacoesPaciente() {
        return ObservacoesPaciente;
    }
    public void setObservacoesPaciente(String observacoesPaciente) {
        ObservacoesPaciente = observacoesPaciente;
    } 
}