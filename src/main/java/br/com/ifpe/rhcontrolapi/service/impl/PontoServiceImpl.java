package br.com.ifpe.rhcontrolapi.service.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.ifpe.rhcontrolapi.model.Funcionario;
import br.com.ifpe.rhcontrolapi.model.Ponto;
import br.com.ifpe.rhcontrolapi.model.enums.StatusPonto;
import br.com.ifpe.rhcontrolapi.repository.PontoRepository;
import br.com.ifpe.rhcontrolapi.service.FuncionarioService;
import br.com.ifpe.rhcontrolapi.service.PontoService;

@Service
public class PontoServiceImpl implements PontoService{

	@Autowired
	PontoRepository repository;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
	
	public List<Ponto> listarPontos() {
		return repository.findAll();
	}

	public Ponto saveOrUpdatePonto(Long codigoFuncionario) throws Exception {
		Optional<Ponto> ponto = repository.findByCodigoFuncionarioAndDate(codigoFuncionario, LocalDate.now());
		if(ponto.isPresent()) {
			return repository.save(fillPoint(ponto.get()));
		} else {
			Funcionario funcionario = funcionarioService.getFuncionarioById(codigoFuncionario);
			Ponto newPonto = new Ponto(funcionario, LocalDate.now(), LocalDateTime.now());
			newPonto.setStatus(StatusPonto.OK);
			return repository.save(newPonto);
		}
	}

	private Ponto fillPoint(Ponto ponto) throws Exception {
		if (ponto.getHoraEntradaInicio() == null) {
			ponto.setHoraEntradaInicio(LocalDateTime.now());
		} else if (ponto.getHoraSaidaAlmoco() == null) {
			ponto.setHoraSaidaAlmoco(LocalDateTime.now());
		} else if (ponto.getHoraEntradaAlmoco() == null) {
			ponto.setHoraEntradaAlmoco(LocalDateTime.now());
		} else {
			ponto.setHorasaidaFim(LocalDateTime.now());
		}
		
		return ponto;
	}

	public Ponto saveOrUpdatePontoAtestado(Long codigoFuncionario, LocalDate data) throws Exception {
		Optional<Ponto> ponto = repository.findByCodigoFuncionarioAndDate(codigoFuncionario, data);
		if(ponto.isPresent()) {
			return repository.save(configPontoAtestado(ponto.get()));
		} else {
			Funcionario funcionario = funcionarioService.getFuncionarioById(codigoFuncionario);
			Ponto newPonto = new Ponto(funcionario, LocalDate.now());
			newPonto.setStatus(StatusPonto.ATESTADO_EM_ANALISE);
			return repository.save(newPonto);
		}
	}
	
	private Ponto configPontoAtestado(Ponto ponto) {
		ponto.setHoraEntradaInicio(null);
		ponto.setHoraSaidaAlmoco(null);
		ponto.setHoraEntradaAlmoco(null);
		ponto.setHorasaidaFim(null);
		ponto.setStatus(StatusPonto.ATESTADO_EM_ANALISE);
		return ponto;
	}

	public void deletePonto(Long codigoPonto) {
		repository.deleteById(codigoPonto);
	}

	public Ponto updateStatusPonto(Long codigoPonto, String status) throws Exception {
		Ponto ponto = repository.findById(codigoPonto).orElseThrow(() -> new Exception("Ponto não encontrado!"));
		ponto.setStatus(StatusPonto.valueOf(StatusPonto.class, status.toUpperCase()));
		
		return repository.save(ponto);
	}

	@Override
	public List<Ponto> listarPontosByCodigoFuncionario(Long codigoFuncionario, LocalDate data) throws Exception {
		LocalDate startDate = convertEndDate(data, "start");
		LocalDate endDate = convertEndDate(data, "end");
		Optional<List<Ponto>> pontos = repository.buscarPontosValidosPorPeriodoEFuncionario(codigoFuncionario, startDate, endDate);
		if(!pontos.isPresent() || pontos.get().isEmpty()) {
			throw new Exception("Nenhum ponto encontrado");
		}
		return pontos.get();
	}

	private LocalDate convertEndDate(LocalDate data, String tipo) {
		if(tipo.equals("end")) {
			Calendar c = Calendar.getInstance();
			Date dataConvertida = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
			c.setTime(dataConvertida);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			return c.getTime().toInstant().atZone(ZoneId.systemDefault() ).toLocalDate();
		} else if(tipo.equals("start")) {
			Calendar c = Calendar.getInstance();
			Date dataConvertida = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
			c.setTime(dataConvertida);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
			return c.getTime().toInstant().atZone(ZoneId.systemDefault() ).toLocalDate();
		}
		return data;
	}

	public boolean checkIfPontoIsClosed(Ponto ponto) {
		if(ponto.getHoraEntradaInicio() == null || ponto.getHoraSaidaAlmoco() == null || ponto.getHoraEntradaAlmoco() == null|| ponto.getHoraSaidaFim() == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public BigDecimal getTotalHourByClosedPonto(Ponto ponto) {
		BigDecimal totalHour = new BigDecimal("0");
		for (int i = 0; i < 2; i++) {
			switch(i) {
			case 0:
				totalHour = totalHour.add(this.calculateHoursBetweenTwoDates(ponto.getHoraEntradaInicio(), ponto.getHoraSaidaAlmoco()));
				break;
			case 1:
				totalHour = totalHour.add(this.calculateHoursBetweenTwoDates(ponto.getHoraEntradaAlmoco(), ponto.getHoraSaidaFim()));
				break;
			}
		}
		
		return totalHour;
	}

	private BigDecimal calculateHoursBetweenTwoDates(LocalDateTime start, LocalDateTime end) {
		Duration duration = Duration.between(start,end);
		return new BigDecimal(duration.getSeconds() / SECONDS_PER_HOUR);
	}

}
