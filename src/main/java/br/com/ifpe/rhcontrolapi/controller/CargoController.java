package br.com.ifpe.rhcontrolapi.controller;

import br.com.ifpe.rhcontrolapi.model.Cargo;
import br.com.ifpe.rhcontrolapi.service.impl.CargoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoServiceImpl service;

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
