package br.com.ifpe.rhcontrolapi.mappers;

import br.com.ifpe.rhcontrolapi.model.Funcionario;
import br.com.ifpe.rhcontrolapi.model.dto.request.FuncionarioRequestDTO;
import br.com.ifpe.rhcontrolapi.model.dto.response.FuncionarioResponseDTO;
import br.com.ifpe.rhcontrolapi.service.impl.CargoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {

    @Autowired
    private CargoServiceImpl cargoService;

    public Funcionario map(FuncionarioRequestDTO funcionarioRequestDTO) throws Exception {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(funcionarioRequestDTO.getNome());
        funcionario.setNomeSocial(funcionarioRequestDTO.getNomeSocial());
        funcionario.setDataNascimento(funcionarioRequestDTO.getDataNascimento());
        funcionario.setCargo(cargoService.getCargoByCodigoCargo(funcionarioRequestDTO.getCodigoCargo()));
        funcionario.setRg(funcionarioRequestDTO.getCpf());
        funcionario.setCpf(funcionarioRequestDTO.getCpf());
        funcionario.setEndereco(funcionarioRequestDTO.getEndereco());

        return funcionario;
    }

    public FuncionarioResponseDTO mapToDTO(Funcionario funcionario) {
        FuncionarioResponseDTO funcionarioResponseDTO = new FuncionarioResponseDTO();

        funcionarioResponseDTO.setCodigoFuncionario(funcionario.getCodigoFuncionario());
        funcionarioResponseDTO.setNome(funcionario.getNome());
        funcionarioResponseDTO.setDataNascimento(funcionario.getDataNascimento());
        funcionarioResponseDTO.setCargo(funcionario.getCargo());
        funcionarioResponseDTO.setNomeSocial(funcionario.getNomeSocial());
        funcionarioResponseDTO.setCpf(funcionario.getCpf());
        funcionarioResponseDTO.setRg(funcionario.getRg());
        funcionarioResponseDTO.setEndereco(funcionario.getEndereco());

        return funcionarioResponseDTO;
    }
}
