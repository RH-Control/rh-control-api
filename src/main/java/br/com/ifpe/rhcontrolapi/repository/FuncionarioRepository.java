package br.com.ifpe.rhcontrolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifpe.rhcontrolapi.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
