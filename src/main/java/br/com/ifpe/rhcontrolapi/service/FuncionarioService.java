package br.com.ifpe.rhcontrolapi.service;

import br.com.ifpe.rhcontrolapi.model.Funcionario;
import br.com.ifpe.rhcontrolapi.model.dto.request.FuncionarioRequestDTO;
import br.com.ifpe.rhcontrolapi.model.dto.response.FuncionarioResponseDTO;

public interface FuncionarioService {

    public FuncionarioResponseDTO saveFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) throws Exception;

    public FuncionarioResponseDTO getFuncionarioDTOById(Long codigoFuncionario) throws Exception;
    
    public Funcionario getFuncionarioById(Long codigoFuncionario) throws Exception;
    
    public FuncionarioResponseDTO updateFuncionario(FuncionarioRequestDTO funcionarioRequestDTO, Long codigoFuncionario) throws Exception;

    public void deleteFuncionario(Long codigoFuncionario);
}
