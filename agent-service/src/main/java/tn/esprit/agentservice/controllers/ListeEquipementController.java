package tn.esprit.agentservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.agentservice.entities.ListeEquipementsParAgent;
import tn.esprit.agentservice.services.IListeEquipement;
import tn.esprit.agentservice.services.ListeEquipementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/liste-equipements")
@AllArgsConstructor
public class ListeEquipementController {
    private final IListeEquipement listeEquipementService;
    @Autowired
    public ListeEquipementController(ListeEquipementService listeEquipementService) {
        this.listeEquipementService = listeEquipementService;
    }

    @PostMapping
    public ResponseEntity<ListeEquipementsParAgent> ajouterEquipementParAgent(@RequestBody ListeEquipementsParAgent equipement) {
        ListeEquipementsParAgent savedEquipement = listeEquipementService.ajouterEquipementParAgent(equipement);
        return new ResponseEntity<>(savedEquipement, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ListeEquipementsParAgent>> getEquipementsParAgent() {
        List<ListeEquipementsParAgent> equipements = listeEquipementService.getEquipementsParAgent();
        return new ResponseEntity<>(equipements, HttpStatus.OK);
    }

    @GetMapping("/{idEquipementParAgent}")
    public ResponseEntity<ListeEquipementsParAgent> getEquipementParAgentById(@PathVariable Long idEquipementParAgent) {
        Optional<ListeEquipementsParAgent> equipement = listeEquipementService.getEquipementParAgentById(idEquipementParAgent);
        return equipement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{idEquipementParAgent}")
    public ResponseEntity<ListeEquipementsParAgent> updateEquipementParAgent(@PathVariable Long idEquipementParAgent, @RequestBody ListeEquipementsParAgent equipement) {
        ListeEquipementsParAgent updatedEquipement = listeEquipementService.updateEquipementParAgent(idEquipementParAgent, equipement);
        return updatedEquipement != null ? ResponseEntity.ok(updatedEquipement) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idEquipementParAgent}")
    public ResponseEntity<Void> deleteEquipementParAgent(@PathVariable Long idEquipementParAgent) {
        listeEquipementService.deleteEquipementParAgent(idEquipementParAgent);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{idEquipement}/affecter/{matricule}")
    public ResponseEntity<Void> affecterAgent(@PathVariable Long idEquipement, @PathVariable Long matricule) {
        listeEquipementService.affecterAgentAEquipement(idEquipement, matricule);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<ListeEquipementsParAgent>> getEquipementsDisponibles() {
        return ResponseEntity.ok(listeEquipementService.getEquipementsDisponibles());
    }

    // Changer le statut d’un équipement
    @PutMapping("/{idEquipement}/statut")
    public ResponseEntity<ListeEquipementsParAgent> changerStatut(@PathVariable Long idEquipement, @RequestParam String nouveauStatut) {
        ListeEquipementsParAgent equipement = listeEquipementService.changerStatutEquipement(idEquipement, nouveauStatut);
        return equipement != null ? ResponseEntity.ok(equipement) : ResponseEntity.notFound().build();
    }
}
