package br.com.ifpe.rhcontrolapi.mappers;

import br.com.ifpe.rhcontrolapi.model.Funcionario;
import br.com.ifpe.rhcontrolapi.model.dto.FuncionarioRequestDTO;
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
        funcionario.setCargo(cargoService.getCargoByCodigoCargo(funcionarioRequestDTO.getCodigoCargo()));
        funcionario.setRg(funcionarioRequestDTO.getCpf());
        funcionario.setCpf(funcionarioRequestDTO.getCpf());
        funcionario.setEndereco(funcionarioRequestDTO.getEndereco());

        return funcionario;
    }
}
