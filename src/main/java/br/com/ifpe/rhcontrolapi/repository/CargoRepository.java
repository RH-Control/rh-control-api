package br.com.ifpe.rhcontrolapi.repository;

import br.com.ifpe.rhcontrolapi.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
