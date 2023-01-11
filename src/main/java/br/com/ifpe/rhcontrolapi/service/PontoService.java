package br.com.ifpe.rhcontrolapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ifpe.rhcontrolapi.model.Ponto;
import br.com.ifpe.rhcontrolapi.model.enums.StatusPonto;

public interface PontoService {

	List<Ponto>listarPontos();

	Ponto saveOrUpdatePonto(Long codigoFuncionario) throws Exception;

	Ponto saveOrUpdatePontoAtestado(Long codigoFuncionario, LocalDate data) throws Exception;
	
	Ponto updateStatusPonto(Long codigoPonto, String status) throws Exception;

	void deletePonto(Long codigoPonto);
	
	List<Ponto> listarPontosByCodigoFuncionario(Long codigoFuncionario, LocalDate data) throws Exception;

	boolean checkIfPontoIsClosed(Ponto ponto);

	BigDecimal getTotalHourByClosedPonto(Ponto ponto);
	
}
