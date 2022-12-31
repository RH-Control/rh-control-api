package br.com.ifpe.rhcontrolapi.service.impl;

import br.com.ifpe.rhcontrolapi.mappers.FuncionarioMapper;
import br.com.ifpe.rhcontrolapi.model.Funcionario;
import br.com.ifpe.rhcontrolapi.model.dto.FuncionarioRequestDTO;
import br.com.ifpe.rhcontrolapi.repository.FuncionarioRepository;
import br.com.ifpe.rhcontrolapi.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FuncionarioMapper mapper;

    public Funcionario saveFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) throws Exception {
        Funcionario funcionario = mapper.map(funcionarioRequestDTO);

        return repository.save(funcionario);
    }

    public Funcionario getFuncionarioById(UUID codigoFuncionario) throws Exception {
        Funcionario funcionario = repository.findById(codigoFuncionario)
                .orElseThrow(() -> new Exception("Funcionário não encontrado!"));

        return funcionario;
    }

    public Funcionario updateFuncionario(FuncionarioRequestDTO funcionarioRequestDTO, UUID codigoFuncionario) throws Exception {
        Boolean hasFuncionario = repository.existsById(codigoFuncionario);
        Funcionario funcionario = mapper.map(funcionarioRequestDTO);

        if (!hasFuncionario)
            throw new Exception("Funcionário não encontrado!");

        return repository.save(funcionario);
    }

    public void deleteFuncionario(UUID codigoFuncionario) {
        repository.deleteById(codigoFuncionario);
    }
}
