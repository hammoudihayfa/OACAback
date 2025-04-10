package tn.esprit.equipementservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.entities.EquipementTransfere;
import tn.esprit.equipementservice.services.IEquipementTransfere;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipement-transfere")
public class EquipementTransfereController {
    @Autowired
    private IEquipementTransfere equipementTransfereService;

    @PostMapping
    public ResponseEntity<EquipementTransfere> createEquipementTransfere(@RequestBody EquipementTransfere equipementTransfere) {
        EquipementTransfere createdEquipement = equipementTransfereService.createEquipementTransfere(equipementTransfere);
        return new ResponseEntity<>(createdEquipement, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipementTransfere> getEquipementTransfereById(@PathVariable Long id) {
        Optional<EquipementTransfere> equipementTransfere = equipementTransfereService.getEquipementTransfereById(id);
        return equipementTransfere.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<EquipementTransfere>> getAllEquipementTransfere() {
        List<EquipementTransfere> equipements = equipementTransfereService.getAllEquipementTransfere();
        return new ResponseEntity<>(equipements, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipementTransfere> updateEquipementTransfere(@PathVariable Long id, @RequestBody EquipementTransfere equipementTransfere) {
        EquipementTransfere updatedEquipement = equipementTransfereService.updateEquipementTransfere(id, equipementTransfere);
        if (updatedEquipement != null) {
            return new ResponseEntity<>(updatedEquipement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipementTransfere(@PathVariable Long id) {
        equipementTransfereService.deleteEquipementTransfere(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
