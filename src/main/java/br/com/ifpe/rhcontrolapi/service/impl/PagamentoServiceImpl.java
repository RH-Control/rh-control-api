package br.com.ifpe.rhcontrolapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.ifpe.rhcontrolapi.config.TopicosKafka;
import br.com.ifpe.rhcontrolapi.model.Funcionario;
import br.com.ifpe.rhcontrolapi.model.Pagamento;
import br.com.ifpe.rhcontrolapi.model.Ponto;
import br.com.ifpe.rhcontrolapi.repository.PagamentoRepository;
import br.com.ifpe.rhcontrolapi.service.FuncionarioService;
import br.com.ifpe.rhcontrolapi.service.PagamentoService;
import br.com.ifpe.rhcontrolapi.service.PontoService;
import br.com.ifpe.rhcontrolapi.service.ProducerService;

@Service
public class PagamentoServiceImpl implements PagamentoService {

	@Autowired
	PagamentoRepository repository;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	PontoService pontoService;
	
	@Autowired
	ProducerService producerService;
	
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
			pagamento.get().setValor(calcularSalario(localDate, pagamento.get()));
			return repository.save(pagamento.get());
		} else {
			Pagamento newPagamento = new Pagamento();
			newPagamento.setCompetencia(this.buildCompetencia(localDate));
			newPagamento.setDataDePagamento(LocalDate.now());
			Funcionario funcionario = funcionarioService.getFuncionarioById(codigoFuncionario);
			newPagamento.setFuncionario(funcionario);
			newPagamento.setValor(calcularSalario(localDate, newPagamento));
			return repository.save(newPagamento);
		}
	}

	private BigDecimal calcularSalario(LocalDate localDate, Pagamento pagamento) throws Exception {
		BigDecimal horaSalario = pagamento.getFuncionario().getCargo().getHoraSalario();
		BigDecimal totalDeHoras = new BigDecimal("0");
		BigDecimal valorPagamento = new BigDecimal("0");
		List<Ponto> pontos = pontoService.listarPontosByCodigoFuncionario(pagamento.getFuncionario().getCodigoFuncionario(), localDate);
		
		for (Ponto ponto : pontos) {
			if(pontoService.checkIfPontoIsClosed(ponto)) {
				totalDeHoras = totalDeHoras.add(pontoService.getTotalHourByClosedPonto(ponto));
			}
		}
		valorPagamento = totalDeHoras.multiply(horaSalario);
		pagamento.setTotalDeHoras(totalDeHoras);
		pagamento.setDataDePagamento(LocalDate.now());
		return valorPagamento;
	}
	

	public Pagamento enviarPagamentoPorEmailByCodigo(Long codigoPagamento) throws Exception {
		Pagamento pagamento = repository.findById(codigoPagamento)
				.orElseThrow(() -> new Exception("Pagamento não encontrado!"));

		ObjectWriter objectWriter = new ObjectMapper().registerModule(new JavaTimeModule()).writer().withDefaultPrettyPrinter();
		String json = objectWriter.writeValueAsString(pagamento);

		producerService.sendMessage(TopicosKafka.SEND_MAIL.getDescricao(), json);
		return pagamento;
	}

	public List<Pagamento> enviarPagamentoPorEmailByCompetencia(String data) throws Exception {
		List<Pagamento> pagamentos = repository.findByDate(data).orElseThrow(() -> new Exception("Pagamento não encontrado!"));
		
		pagamentos.stream().forEach(p -> {
			ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).writer().withDefaultPrettyPrinter();
			String json = "";
			try {
				json = ow.writeValueAsString(p);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			producerService.sendMessage(TopicosKafka.SEND_MAIL.getDescricao(), json);
		});
		return pagamentos;
	}

	private LocalDate buildCompetencia(LocalDate localDate) {
		Calendar c = Calendar.getInstance();
		Date data = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		c.setTime(data);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		LocalDate comp = c.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return comp;
	}
}
