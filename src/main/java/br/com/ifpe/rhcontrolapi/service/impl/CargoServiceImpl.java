package br.com.ifpe.rhcontrolapi.service.impl;

import br.com.ifpe.rhcontrolapi.model.Cargo;
import br.com.ifpe.rhcontrolapi.repository.CargoRepository;
import br.com.ifpe.rhcontrolapi.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Boolean hasCargo = repository.existsById(codigoCargo);

        if (!hasCargo)
            throw new Exception("Cargo não existe!");

        return repository.save(cargo);
    }

    public void deleteCargo(Long codigoCargo) {
        repository.deleteById(codigoCargo);
    }
}
