package br.com.ifpe.rhcontrolapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ifpe.rhcontrolapi.model.Ponto;

public interface PontoRepository  extends JpaRepository<Ponto, Long> {

	@Query(value = "SELECT * FROM ponto WHERE codigo_funcionario = ?1 AND data = ?2", nativeQuery = true)
	Optional<Ponto> findByCodigoFuncionarioAndDate(Long codigoFuncionario, LocalDate localDate);

	@Query(value = "SELECT * FROM ponto WHERE codigo_funcionario = ?1 AND data >= ?2 and data <= ?3", nativeQuery = true)
	Optional<List<Ponto>> buscarPontosPorPeriodoEFuncionario(Long codigoFuncionario, LocalDate startDate, LocalDate endDate);

}
