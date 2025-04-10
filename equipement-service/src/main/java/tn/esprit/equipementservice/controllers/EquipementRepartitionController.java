package tn.esprit.equipementservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.entities.EquipementRepartition;
import tn.esprit.equipementservice.services.EquipementRepartitionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipements-repartition")
public class EquipementRepartitionController {
    private final EquipementRepartitionService equipementRepartitionService;

    @Autowired
    public EquipementRepartitionController(EquipementRepartitionService equipementRepartitionService) {
        this.equipementRepartitionService = equipementRepartitionService;
    }

    @PostMapping
    public ResponseEntity<EquipementRepartition> createEquipementRepartition(@RequestBody EquipementRepartition equipementRepartition) {
        EquipementRepartition createdEquipementRepartition = equipementRepartitionService.createEquipementRepartition(equipementRepartition);
        return new ResponseEntity<>(createdEquipementRepartition, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EquipementRepartition>> getAllEquipementsRepartition() {
        List<EquipementRepartition> equipementsRepartition = equipementRepartitionService.getAllEquipementsRepartition();
        return new ResponseEntity<>(equipementsRepartition, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipementRepartition> getEquipementRepartitionById(@PathVariable Long id) {
        Optional<EquipementRepartition> equipementRepartition = equipementRepartitionService.getEquipementRepartitionById(id);
        return equipementRepartition.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipementRepartition> updateEquipementRepartition(
            @PathVariable Long id,
            @RequestBody EquipementRepartition equipementRepartition) {
        EquipementRepartition updatedEquipementRepartition = equipementRepartitionService.updateEquipementRepartition(id, equipementRepartition);
        return updatedEquipementRepartition != null
                ? new ResponseEntity<>(updatedEquipementRepartition, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipementRepartition(@PathVariable Long id) {
        equipementRepartitionService.deleteEquipementRepartition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
