package com.group.vitalmedapi.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private String Email;
    private String ObservacoesPaciente;



    
    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Cirurgia> cirurgias;

    public Paciente(){}
    public Paciente(String nome, Date dataNascimento, String telefone, String endereco, String email, String observacoesPaciente) {
        Nome = nome;
        DataNascimento = dataNascimento;
        Telefone = telefone;
        Endereco = endereco;
        Email = email;
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
    
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
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
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
     
    public List<Cirurgia> getCirurgias() {
        return cirurgias;
    }
    public void setCirurgias(List<Cirurgia> cirurgias) {
        this.cirurgias = cirurgias;
    }
}
