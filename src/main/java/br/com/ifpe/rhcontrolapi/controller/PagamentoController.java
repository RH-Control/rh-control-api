package br.com.ifpe.rhcontrolapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.rhcontrolapi.model.Pagamento;
import br.com.ifpe.rhcontrolapi.service.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	PagamentoService service;
	
	@GetMapping
	public ResponseEntity<List<Pagamento>> listarTodosPagamentos() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(service.listarTodosPagamentos());
	}
	
	@GetMapping("/{codigoFuncionario}")
	public ResponseEntity<List<Pagamento>> listarPagamentos(@PathVariable Long codigoFuncionario) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(service.listarPagamentos(codigoFuncionario));
	}
	
	@PostMapping("/gerar")
	public ResponseEntity<Pagamento> gerarPagamentos(@RequestHeader Long codigoFuncionario, @RequestHeader String data) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(service.gerarPagamento(codigoFuncionario, data));
	}
	
	@PostMapping("/enviar/{codigoPagamento}")
	public ResponseEntity<Pagamento> enviarPagamentoPorEmailByCodigo(@PathVariable Long codigoPagamento) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(service.enviarPagamentoPorEmailByCodigo(codigoPagamento));
	}
	
	@PostMapping("/enviar")
	public ResponseEntity<List<Pagamento>> enviarPagamentoPorEmailByCompetencia(@RequestHeader String data) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(service.enviarPagamentoPorEmailByCompetencia(data));
	}

}
