package tn.esprit.equipementservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.entities.EquipementReforme;
import tn.esprit.equipementservice.services.IEquipementEnReforme;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipementsReformes")
public class EquipementReformeController {
    @Autowired
    private IEquipementEnReforme equipementEnReformeService;

    @PostMapping
    public ResponseEntity<EquipementReforme> createEquipementReforme(@RequestBody EquipementReforme equipementReforme) {
        EquipementReforme createdEquipement = equipementEnReformeService.createEquipementReforme(equipementReforme);
        return new ResponseEntity<>(createdEquipement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipementReforme> getEquipementReformeById(@PathVariable Long id) {
        Optional<EquipementReforme> equipementReforme = equipementEnReformeService.getEquipementReformeById(id);
        return equipementReforme.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<EquipementReforme>> getAllEquipementsReformes() {
        List<EquipementReforme> equipementsReformes = equipementEnReformeService.getAllEquipementsReformes();
        return new ResponseEntity<>(equipementsReformes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipementReforme> updateEquipementReforme(
            @PathVariable Long id,
            @RequestBody EquipementReforme equipementReforme) {
        try {
            EquipementReforme updatedEquipement = equipementEnReformeService.updateEquipementReforme(id, equipementReforme);
            return new ResponseEntity<>(updatedEquipement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipementReforme(@PathVariable Long id) {
        try {
            equipementEnReformeService.deleteEquipementReforme(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
