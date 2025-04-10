package tn.esprit.equipementservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.entities.EquipementEnService;
import tn.esprit.equipementservice.services.IEquipementEnService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipements-en-service")


public class EquipementEnServiceController {
    private final IEquipementEnService equipementEnService;
    @Autowired
    public EquipementEnServiceController(IEquipementEnService equipementEnService) {
        this.equipementEnService = equipementEnService;
    }

    @PostMapping
    public ResponseEntity<EquipementEnService> ajouterEquipement(@RequestBody EquipementEnService equipement) {
        EquipementEnService nouvelEquipement = equipementEnService.ajouterEquipementEnService(equipement);
        return ResponseEntity.ok(nouvelEquipement);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EquipementEnService> modifierEquipement(@PathVariable Long id, @RequestBody EquipementEnService equipement) {
        EquipementEnService equipementModifie = equipementEnService.modifierEquipementEnService(id, equipement);
        return ResponseEntity.ok(equipementModifie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEquipement(@PathVariable Long id) {
        equipementEnService.supprimerEquipementEnService(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipementEnService> obtenirEquipementParId(@PathVariable Long id) {
        Optional<EquipementEnService> equipement = equipementEnService.obtenirEquipementEnServiceParId(id);
        return equipement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EquipementEnService>> obtenirTousLesEquipements() {
        List<EquipementEnService> equipements = equipementEnService.obtenirTousLesEquipementsEnService();
        return ResponseEntity.ok(equipements);
    }
}
