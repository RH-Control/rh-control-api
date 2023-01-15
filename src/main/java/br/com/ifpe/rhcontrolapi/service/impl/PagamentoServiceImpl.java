package br.com.ifpe.rhcontrolapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.rhcontrolapi.model.Funcionario;
import br.com.ifpe.rhcontrolapi.model.Pagamento;
import br.com.ifpe.rhcontrolapi.model.Ponto;
import br.com.ifpe.rhcontrolapi.repository.PagamentoRepository;
import br.com.ifpe.rhcontrolapi.service.FuncionarioService;
import br.com.ifpe.rhcontrolapi.service.PagamentoService;
import br.com.ifpe.rhcontrolapi.service.PontoService;

@Service
public class PagamentoServiceImpl implements PagamentoService {

	@Autowired
	PagamentoRepository repository;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	PontoService pontoService;
	
	@Override
	public List<Pagamento> listarTodosPagamentos() {
		return repository.findAll();
	}

	@Override
	public List<Pagamento> listarPagamentos(Long codigoFuncionario) {
		return repository.findByCodigoFuncionario(codigoFuncionario);
	}

	@Override
	public Pagamento gerarPagamento(Long codigoFuncionario, String data) throws Exception {
		LocalDate localDate = LocalDate.parse(data);
		Optional<Pagamento> pagamento = repository.findByCodigoFuncionarioAndDate(codigoFuncionario, localDate);
		
		if(pagamento.isPresent()) {
			pagamento.get().setValor(calcularSalario(localDate, pagamento.get().getFuncionario()));
			return repository.save(pagamento.get());
		} else {
			Pagamento newPagamento = new Pagamento();
			newPagamento.setCompetencia(localDate);
			newPagamento.setDataDePagamento(LocalDate.now());
			Funcionario funcionario = funcionarioService.getFuncionarioById(codigoFuncionario);
			newPagamento.setFuncionario(funcionario);
			newPagamento.setValor(calcularSalario(localDate, funcionario));
			return repository.save(newPagamento);
		}
	}

	private BigDecimal calcularSalario(LocalDate localDate, Funcionario funcionario) throws Exception {
		BigDecimal horaSalario = funcionario.getCargo().getHoraSalario();
		BigDecimal totalDeHoras = new BigDecimal("0");
		BigDecimal pagamento = new BigDecimal("0");
		List<Ponto> pontos = pontoService.listarPontosByCodigoFuncionario(funcionario.getCodigoFuncionario(), localDate);
		
		for (Ponto ponto : pontos) {
			if(pontoService.checkIfPontoIsClosed(ponto)) {
				totalDeHoras = totalDeHoras.add(pontoService.getTotalHourByClosedPonto(ponto));
			}
		}
		pagamento = totalDeHoras.multiply(horaSalario);
		
		return pagamento;
	}

	public Pagamento enviarPagamentoPorEmailByCodigo(Long codigoPagamento) throws Exception {
		Pagamento pagamento = repository.findById(codigoPagamento)
                .orElseThrow(() -> new Exception("Pagamento não encontrado!"));
		//TODO: enviar o pagamento para o microsserviço de e-mail
		return pagamento;
	}

	public List<Pagamento> enviarPagamentoPorEmailByCompetencia(String data) throws Exception {
		List<Pagamento> pagamentos = repository.findByDate(data).orElseThrow(() -> new Exception("Pagamento não encontrado!"));
		//TODO: enviar os pagamentos para o microsserviço de e-mail
		return pagamentos;
	}

}
