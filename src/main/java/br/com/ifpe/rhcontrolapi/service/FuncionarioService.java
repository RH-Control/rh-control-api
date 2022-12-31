package br.com.ifpe.rhcontrolapi.service;

import br.com.ifpe.rhcontrolapi.model.Funcionario;
import br.com.ifpe.rhcontrolapi.model.dto.FuncionarioRequestDTO;

import java.util.UUID;

public interface FuncionarioService {

    public Funcionario saveFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) throws Exception;

    public Funcionario getFuncionarioById(UUID codigoFuncionario) throws Exception;

    public Funcionario updateFuncionario(FuncionarioRequestDTO funcionarioRequestDTO, UUID codigoFuncionario) throws Exception;

    public void deleteFuncionario(UUID codigoFuncionario);
}
