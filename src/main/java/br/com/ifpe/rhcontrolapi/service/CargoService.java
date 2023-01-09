package br.com.ifpe.rhcontrolapi.service;

import java.util.List;

import br.com.ifpe.rhcontrolapi.model.Cargo;

public interface CargoService {
	
	public List<Cargo> listarCargos() throws Exception;
	
    public Cargo saveCargo(Cargo cargo);

    public Cargo getCargoByCodigoCargo(Long codigoCargo) throws Exception;

    public Cargo updateCargo(Cargo cargo, Long codigoCargo) throws Exception;

    public void deleteCargo(Long codigoCargo);
}
