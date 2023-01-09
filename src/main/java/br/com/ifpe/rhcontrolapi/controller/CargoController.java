package br.com.ifpe.rhcontrolapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.rhcontrolapi.model.Cargo;
import br.com.ifpe.rhcontrolapi.service.CargoService;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoService service;

    @GetMapping
    public ResponseEntity<List<Cargo>> listarCargos() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarCargos());
    }
    
    @GetMapping("/{codigoCargo}")
    public ResponseEntity<Cargo> getCargo(@PathVariable Long codigoCargo) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCargoByCodigoCargo(codigoCargo));
    }

    @PostMapping
    public ResponseEntity<Cargo> saveCargo(@RequestBody Cargo cargo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCargo(cargo));
    }

    @PutMapping("/{codigoCargo}")
    public ResponseEntity<Cargo> updateCargo(@PathVariable Long codigoCargo, @RequestBody Cargo cargo) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateCargo(cargo, codigoCargo));
    }

    @DeleteMapping("/{codigoCargo}")
    public void deleteCargo(@PathVariable Long codigoCargo) {
        service.deleteCargo(codigoCargo);
    }
}
