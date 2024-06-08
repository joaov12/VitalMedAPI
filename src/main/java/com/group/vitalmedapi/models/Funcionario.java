package com.group.vitalmedapi.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Nome;
    private Date DataNascimento;
    private Date DataContratacao;
    private double Salario;
    private String Telefone;
    private String Endereco;
    private String Email;
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    @JsonIgnore
    private Departamento departamento;

    

    public Funcionario() {
    }
    public Funcionario(String nome, Date dataNascimento, Date dataContratacao, double salario, String telefone,
            String endereco, String email,  Departamento departamento) {
        Nome = nome;
        DataNascimento = dataNascimento;
        DataContratacao = dataContratacao;
        Salario = salario;
        Telefone = telefone;
        Endereco = endereco;
        Email = email;
        this.departamento = departamento;
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
    public Date getDataContratacao() {
        return DataContratacao;
    }
    public void setDataContratacao(Date dataContratacao) {
        DataContratacao = dataContratacao;
    }
    public double getSalario() {
        return Salario;
    }
    public void setSalario(double salario) {
        Salario = salario;
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
    
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    
}
