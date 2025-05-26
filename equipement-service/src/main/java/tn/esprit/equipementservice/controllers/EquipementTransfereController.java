package tn.esprit.equipementservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.entities.EquipementTransfere;
import tn.esprit.equipementservice.services.IEquipementTransfere;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipements-transferts")
@CrossOrigin(origins = "*")
public class EquipementTransfereController {

    private final IEquipementTransfere equipementTransfereService;
    public EquipementTransfereController(IEquipementTransfere equipementTransfereService) {
        this.equipementTransfereService = equipementTransfereService;
    }
    @PostMapping
    public ResponseEntity<EquipementTransfere> addEquipementTransfere(@RequestBody EquipementTransfere equipementTransfere) {
        EquipementTransfere saved = equipementTransfereService.addEquipementTransfere(equipementTransfere);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipementTransfere> getEquipementTransfereById(@PathVariable Long id) {
        Optional<EquipementTransfere> optional = equipementTransfereService.getEquipementTransfereById(id);
        return optional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EquipementTransfere>> getAllEquipementsTransferes() {
        List<EquipementTransfere> list = equipementTransfereService.getAllEquipementsTransferes();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipementTransfere> updateEquipementTransfere(@PathVariable Long id,
                                                                         @RequestBody EquipementTransfere equipementTransfere) {
        try {
            EquipementTransfere updated = equipementTransfereService.updateEquipementTransfere(id, equipementTransfere);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipementTransfere(@PathVariable Long id) {
        try {
            equipementTransfereService.deleteEquipementTransfere(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/equipement/{equipementId}")
    public ResponseEntity<List<EquipementTransfere>> getEquipementsTransferesByEquipementId(@PathVariable Long equipementId) {
        List<EquipementTransfere> list = equipementTransfereService.getEquipementsTransferesByEquipementId(equipementId);
        return ResponseEntity.ok(list);
    }
}
