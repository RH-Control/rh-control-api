package br.com.ifpe.rhcontrolapi.service;

import br.com.ifpe.rhcontrolapi.model.dto.request.FuncionarioRequestDTO;
import br.com.ifpe.rhcontrolapi.model.dto.response.FuncionarioResponseDTO;

import java.util.UUID;

public interface FuncionarioService {

    public FuncionarioResponseDTO saveFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) throws Exception;

    public FuncionarioResponseDTO getFuncionarioById(UUID codigoFuncionario) throws Exception;

    public FuncionarioResponseDTO updateFuncionario(FuncionarioRequestDTO funcionarioRequestDTO, UUID codigoFuncionario) throws Exception;

    public void deleteFuncionario(UUID codigoFuncionario);
}
