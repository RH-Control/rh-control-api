package br.com.ifpe.rhcontrolapi.controller;

import br.com.ifpe.rhcontrolapi.model.dto.request.FuncionarioRequestDTO;
import br.com.ifpe.rhcontrolapi.model.dto.response.FuncionarioResponseDTO;
import br.com.ifpe.rhcontrolapi.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("/{codigoFuncionario}")
    public ResponseEntity<FuncionarioResponseDTO> getFuncionario(@PathVariable UUID codigoFuncionario) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.getFuncionarioById(codigoFuncionario));
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> saveFuncionario(@RequestBody FuncionarioRequestDTO funcionarioRequestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveFuncionario(funcionarioRequestDTO));
    }

    @PutMapping("/{codigoFuncionario}")
    public ResponseEntity<FuncionarioResponseDTO> updateFuncionario(@PathVariable UUID codigoFuncionario, @RequestBody FuncionarioRequestDTO funcionarioRequestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateFuncionario(funcionarioRequestDTO, codigoFuncionario));
    }

    @DeleteMapping("/{codigoFuncionario}")
    public void deleteFuncionario(@PathVariable UUID codigoFuncionario) {
        service.deleteFuncionario(codigoFuncionario);
    }
}
