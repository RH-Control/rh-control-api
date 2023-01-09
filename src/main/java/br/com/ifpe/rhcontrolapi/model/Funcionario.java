package br.com.ifpe.rhcontrolapi.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Funcionario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoFuncionario;
    private String nome;
    private String nomeSocial;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Cargo cargo;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String rg;
    private Endereco endereco;

    public Funcionario() {
    }

    public Funcionario(Long codigoFuncionario, String nome, String nomeSocial, LocalDate dataNascimento, Cargo cargo, String cpf, String rg, Endereco endereco) {
        this.codigoFuncionario = codigoFuncionario;
        this.nome = nome;
        this.nomeSocial = nomeSocial;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
    }

    public Long getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(Long codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
