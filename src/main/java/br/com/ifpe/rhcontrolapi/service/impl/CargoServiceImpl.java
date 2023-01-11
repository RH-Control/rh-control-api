package br.com.ifpe.rhcontrolapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.rhcontrolapi.model.Cargo;
import br.com.ifpe.rhcontrolapi.repository.CargoRepository;
import br.com.ifpe.rhcontrolapi.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService {

	@Autowired
	CargoRepository repository;

	public Cargo saveCargo(Cargo cargo) {
		return repository.save(cargo);
	}

	public Cargo getCargoByCodigoCargo(Long codigoCargo) throws Exception {
		Cargo cargo = repository.findById(codigoCargo).orElseThrow(() -> new Exception("Cargo não existe!"));

		return cargo;
	}

	public Cargo updateCargo(Cargo cargo, Long codigoCargo) throws Exception {
		Optional<Cargo> existingCargo = repository.findById(codigoCargo);

		if (!existingCargo.isPresent()) {
			throw new Exception("Cargo não existe!");
		}
		existingCargo.get().setHoraSalario(cargo.getHoraSalario());
		existingCargo.get().setNome(cargo.getNome());

		return repository.save(existingCargo.get());
	}

	public void deleteCargo(Long codigoCargo) {
		repository.deleteById(codigoCargo);
	}

	public List<Cargo> listarCargos() throws Exception {
		return repository.findAll();
	}
}
