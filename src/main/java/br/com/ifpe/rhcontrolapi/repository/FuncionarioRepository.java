package br.com.ifpe.rhcontrolapi.repository;

import br.com.ifpe.rhcontrolapi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
}
