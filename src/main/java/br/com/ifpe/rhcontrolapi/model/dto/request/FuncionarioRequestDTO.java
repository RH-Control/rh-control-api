package br.com.ifpe.rhcontrolapi.model.dto.request;

import br.com.ifpe.rhcontrolapi.model.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class FuncionarioRequestDTO {

    private String nome;
    private String nomeSocial;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Long codigoCargo;
    private String cpf;
    private String rg;
    private Endereco endereco;
    private String email;

    public FuncionarioRequestDTO() {
    }

    public FuncionarioRequestDTO(String nome, String nomeSocial, LocalDate dataNascimento, Long codigoCargo, String cpf, String rg, Endereco endereco) {
        this.nome = nome;
        this.nomeSocial = nomeSocial;
        this.dataNascimento = dataNascimento;
        this.codigoCargo = codigoCargo;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
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

    public Long getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Long codigoCargo) {
        this.codigoCargo = codigoCargo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
