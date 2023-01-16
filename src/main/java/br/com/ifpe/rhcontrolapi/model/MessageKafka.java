package br.com.ifpe.rhcontrolapi.model;

import java.io.Serializable;
import java.util.List;

public class MessageKafka implements Serializable {

    private static final long serialVersionUID = -4493501206547535071L;

    private Long codigoFuncionario;
    private List<DiaAbonado> diasAbonados;
    private String status;

    public Long getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(Long codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public List<DiaAbonado> getDiasAbonados() {
        return diasAbonados;
    }

    public void setDiasAbonados(List<DiaAbonado> diasAbonados) {
        this.diasAbonados = diasAbonados;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
