package br.com.ifpe.rhcontrolapi.model.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.rhcontrolapi.model.Cargo;
import br.com.ifpe.rhcontrolapi.model.Endereco;

public class FuncionarioResponseDTO {

    private Long codigoFuncionario;
    private String nome;
    private String nomeSocial;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Cargo cargo;
    private String cpf;
    private String rg;
    private Endereco endereco;


    public FuncionarioResponseDTO() {
    }

    public FuncionarioResponseDTO(Long codigoFuncionario, String nome, String nomeSocial, LocalDate dataNascimento, Cargo cargo, String cpf, String rg, Endereco endereco) {
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
