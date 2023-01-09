package br.com.ifpe.rhcontrolapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.rhcontrolapi.model.dto.request.FuncionarioRequestDTO;
import br.com.ifpe.rhcontrolapi.model.dto.response.FuncionarioResponseDTO;
import br.com.ifpe.rhcontrolapi.service.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("/{codigoFuncionario}")
    public ResponseEntity<FuncionarioResponseDTO> getFuncionario(@PathVariable Long codigoFuncionario) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.getFuncionarioDTOById(codigoFuncionario));
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> saveFuncionario(@RequestBody FuncionarioRequestDTO funcionarioRequestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveFuncionario(funcionarioRequestDTO));
    }

    @PutMapping("/{codigoFuncionario}")
    public ResponseEntity<FuncionarioResponseDTO> updateFuncionario(@PathVariable Long codigoFuncionario, @RequestBody FuncionarioRequestDTO funcionarioRequestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateFuncionario(funcionarioRequestDTO, codigoFuncionario));
    }

    @DeleteMapping("/{codigoFuncionario}")
    public void deleteFuncionario(@PathVariable Long codigoFuncionario) {
        service.deleteFuncionario(codigoFuncionario);
    }
}
