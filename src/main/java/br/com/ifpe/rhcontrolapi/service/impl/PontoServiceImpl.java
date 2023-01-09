package br.com.ifpe.rhcontrolapi.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

}
