package tn.esprit.equipementservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.entities.EquipementEnStock;
import tn.esprit.equipementservice.services.IEquipementEnStock;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/equipements-en-stock")
public class EquipementEnStockController {
    @Autowired
    private IEquipementEnStock equipementEnStockService;

    @PostMapping
    public ResponseEntity<EquipementEnStock> createEquipement(@RequestBody EquipementEnStock equipement) {
        EquipementEnStock createdEquipement = equipementEnStockService.createEquipement(equipement);
        return new ResponseEntity<>(createdEquipement, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EquipementEnStock> getEquipementById(@PathVariable Long id) {
        Optional<EquipementEnStock> equipement = equipementEnStockService.getEquipementById(id);
        return equipement.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<EquipementEnStock>> getAllEquipements() {
        List<EquipementEnStock> equipements = equipementEnStockService.getAllEquipements();
        return new ResponseEntity<>(equipements, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipementEnStock> updateEquipement(@PathVariable Long id, @RequestBody EquipementEnStock equipement) {
        try {
            EquipementEnStock updatedEquipement = equipementEnStockService.updateEquipement(id, equipement);
            return new ResponseEntity<>(updatedEquipement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long id) {
        try {
            equipementEnStockService.deleteEquipement(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
