package br.com.ifpe.rhcontrolapi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.rhcontrolapi.mappers.FuncionarioMapper;
import br.com.ifpe.rhcontrolapi.model.Funcionario;
import br.com.ifpe.rhcontrolapi.model.dto.request.FuncionarioRequestDTO;
import br.com.ifpe.rhcontrolapi.model.dto.response.FuncionarioResponseDTO;
import br.com.ifpe.rhcontrolapi.repository.FuncionarioRepository;
import br.com.ifpe.rhcontrolapi.service.CargoService;
import br.com.ifpe.rhcontrolapi.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FuncionarioMapper mapper;
    
    @Autowired
    CargoService cargoService;

    public FuncionarioResponseDTO saveFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) throws Exception {
        Funcionario funcionario = mapper.map(funcionarioRequestDTO);
        Funcionario funcionarioSaved = repository.save(funcionario);

        return  mapper.mapToDTO(funcionarioSaved);
    }

    public FuncionarioResponseDTO getFuncionarioDTOById(Long codigoFuncionario) throws Exception {
        Funcionario funcionario = repository.findById(codigoFuncionario)
                .orElseThrow(() -> new Exception("Funcionário não encontrado!"));

        return mapper.mapToDTO(funcionario);
    }

    public FuncionarioResponseDTO updateFuncionario(FuncionarioRequestDTO funcionarioRequestDTO, Long codigoFuncionario) throws Exception {
        Optional<Funcionario> existingFuncionario = repository.findById(codigoFuncionario);
        if (!existingFuncionario.isPresent()) {
        	throw new Exception("Funcionário não encontrado!");
        }
        Funcionario funcionario = existingFuncionario.get();
        
        funcionario.setNome(funcionarioRequestDTO.getNome());
        funcionario.setNomeSocial(funcionarioRequestDTO.getNomeSocial());
        funcionario.setDataNascimento(funcionarioRequestDTO.getDataNascimento());
        funcionario.setRg(funcionarioRequestDTO.getRg());
        funcionario.setEndereco(funcionarioRequestDTO.getEndereco());
        funcionario.setCpf(funcionarioRequestDTO.getCpf());
        funcionario.setCargo(cargoService.getCargoByCodigoCargo(funcionarioRequestDTO.getCodigoCargo()));
        
        repository.save(funcionario);
        return mapper.mapToDTO(funcionario);
    }

    public void deleteFuncionario(Long codigoFuncionario) {
        repository.deleteById(codigoFuncionario);
    }

	public Funcionario getFuncionarioById(Long codigoFuncionario) throws Exception {
		 Funcionario funcionario = repository.findById(codigoFuncionario)
	                .orElseThrow(() -> new Exception("Funcionário não encontrado!"));
		return funcionario;
	}
}
