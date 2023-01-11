package br.com.ifpe.rhcontrolapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ifpe.rhcontrolapi.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

	@Query(value = "SELECT * FROM pagamento WHERE codigo_funcionario = ?1", nativeQuery = true)
	List<Pagamento> findByCodigoFuncionario(Long codigoFuncionario);

	@Query(value = "SELECT * FROM pagamento WHERE codigo_funcionario = ?1 AND data = ?2", nativeQuery = true)
	Optional<Pagamento> findByCodigoFuncionarioAndDate(Long codigoFuncionario, LocalDate data);
	
}
