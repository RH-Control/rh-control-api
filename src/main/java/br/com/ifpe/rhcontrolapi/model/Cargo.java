package br.com.ifpe.rhcontrolapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigoCargo;
    private String nome;
    private BigDecimal horaSalario;

    public Cargo() {
    }

    public Cargo(Long codigoCargo, String nome, BigDecimal horaSalario) {
        this.codigoCargo = codigoCargo;
        this.nome = nome;
        this.horaSalario = horaSalario;
    }

    public Long getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Long codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getHoraSalario() {
        return horaSalario;
    }

    public void setHoraSalario(BigDecimal horaSalario) {
        this.horaSalario = horaSalario;
    }
}
