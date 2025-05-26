package tn.esprit.equipementservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.entities.EtatEquipement;
import tn.esprit.equipementservice.services.IEquipementEtat;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/etat-equipement")
public class EquipementEtatController {

    @Autowired
    private IEquipementEtat equipementEtatService;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleParsingError(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Erreur de format JSON: " + ex.getMessage());
    }

    @PostMapping
    public ResponseEntity<EtatEquipement> ajouterEtatEquipement(@RequestBody EtatEquipement etatEquipement) {
        return ResponseEntity.ok(equipementEtatService.ajouterEtatEquipement(etatEquipement));
    }

    @GetMapping
    public ResponseEntity<List<EtatEquipement>> recupererTous() {
        return ResponseEntity.ok(equipementEtatService.recupererTous());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtatEquipement> recupererParId(@PathVariable Long id) {
        Optional<EtatEquipement> etatEquipement = equipementEtatService.recupererParId(id);
        return etatEquipement.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtatEquipement> mettreAJourEtatEquipement(
            @PathVariable Long id,
            @RequestBody EtatEquipement etatEquipements) {
        EtatEquipement updatedEtat = equipementEtatService.mettreAJourEtatEquipement(id, etatEquipements);
        return (updatedEtat != null) ? ResponseEntity.ok(updatedEtat) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEtatEquipement(@PathVariable Long id) {
        equipementEtatService.supprimerEtatEquipement(id);
        return ResponseEntity.noContent().build();
    }
}
