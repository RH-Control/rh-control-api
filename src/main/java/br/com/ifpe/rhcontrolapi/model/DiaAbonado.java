package br.com.ifpe.rhcontrolapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DiaAbonado {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate diaAbonado;

    public LocalDate getDiaAbonado() {
        return diaAbonado;
    }

    public void setDiaAbonado(LocalDate diaAbonado) {
        this.diaAbonado = diaAbonado;
    }
}
