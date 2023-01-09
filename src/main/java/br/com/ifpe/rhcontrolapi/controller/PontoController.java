package br.com.ifpe.rhcontrolapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifpe.rhcontrolapi.model.Ponto;
import br.com.ifpe.rhcontrolapi.service.PontoService;

@Controller
@RequestMapping("/ponto")
public class PontoController {

	@Autowired
	PontoService service;
	
	@GetMapping
    public ResponseEntity<List<Ponto>> listarPontos() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarPontos());
    }
	
	@PostMapping("/{codigoFuncionario}")
	public ResponseEntity<Ponto> savePonto(@PathVariable Long codigoFuncionario) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveOrUpdatePonto(codigoFuncionario));
    }
	
	@PutMapping("/{codigoPonto}")
	public ResponseEntity<Ponto> updateStatusPonto(@PathVariable Long codigoPonto, @RequestHeader String status) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateStatusPonto(codigoPonto, status));
    }
	
	@DeleteMapping("/{codigoPonto}")
    public void deleteCargo(@PathVariable Long codigoPonto) {
        service.deletePonto(codigoPonto);
    }
	
}