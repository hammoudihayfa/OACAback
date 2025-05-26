package tn.esprit.equipementservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.DTO.EquipementMaintenanceDTO;
import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.entities.HistoriquePanne;
import tn.esprit.equipementservice.repositories.EquipementEnPanneRepository;
import tn.esprit.equipementservice.services.EquipementMaintenanceService;
import tn.esprit.equipementservice.services.HistoriquePanneService;
import tn.esprit.equipementservice.services.IEquipementEnPanne;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/equipements-en-panne")
public class EquipementEnPanneController {
    @Autowired
    private final IEquipementEnPanne iEquipementEnPanne;
    @Autowired
    private final EquipementMaintenanceService equipementMaintenanceService;
    @Autowired
    private HistoriquePanneService historiqueService;
    private final EquipementEnPanneRepository equipementRepo;



    public EquipementEnPanneController(IEquipementEnPanne iEquipementEnPanne,
                                       EquipementMaintenanceService equipementMaintenanceService,
                                       EquipementEnPanneRepository equipementRepo) {
        this.iEquipementEnPanne = iEquipementEnPanne;
        this.equipementMaintenanceService = equipementMaintenanceService;
        this.equipementRepo = equipementRepo;
    }
    @GetMapping("/programme-maintenance/{idEquipement}")
    public ResponseEntity<EquipementMaintenanceDTO> getEquipementAvecMaintenance(@PathVariable Long idEquipement) {
        EquipementMaintenanceDTO dto = equipementMaintenanceService.getEquipementAvecMaintenance(idEquipement);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<String> ajouterEquipementEnPanne(@RequestBody EquipementEnPanne equipementEnPanne) {
        iEquipementEnPanne.ajouterEquipementEnPanne(equipementEnPanne);
        historiqueService.ajouterEtape(equipementEnPanne, equipementEnPanne.getStatut(), "Création d'une panne", "Admin");
        return ResponseEntity.ok("Équipement en panne ajouté avec succès");
    }



    @GetMapping
    public ResponseEntity<List<EquipementEnPanne>> getAllEquipementsEnPanne() {
        List<EquipementEnPanne> equipementsEnPanne = iEquipementEnPanne.listeEquipementsEnPanne();
        return new ResponseEntity<>(equipementsEnPanne, HttpStatus.OK);
    }


    @GetMapping("/{idEquipementEnPane}")
    public ResponseEntity<EquipementEnPanne> getEquipementEnPanneById(@PathVariable Long idEquipementEnPane) {
        Optional<EquipementEnPanne> equipementEnPanne = iEquipementEnPanne.getEquipementEnPanneById(idEquipementEnPane);
        if (equipementEnPanne.isPresent()) {
            return new ResponseEntity<>(equipementEnPanne.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idEquipementEnPane}")
    public ResponseEntity<EquipementEnPanne> updateEquipementEnPanne(
            @PathVariable Long idEquipementEnPane, @RequestBody EquipementEnPanne equipementEnPanne) {
        EquipementEnPanne updatedEquipementEnPanne = iEquipementEnPanne.updateEquipementEnPanne(idEquipementEnPane, equipementEnPanne);
        if (updatedEquipementEnPanne != null) {
            return new ResponseEntity<>(updatedEquipementEnPanne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idEquipementEnPane}")
    public ResponseEntity<Void> deleteEquipementEnPanne(@PathVariable Long idEquipementEnPane) {
        iEquipementEnPanne.deleteEquipementEnPanne(idEquipementEnPane);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/failure-rate")
    public ResponseEntity<Double> getGlobalFailureRate() {
        double rate = iEquipementEnPanne.getGlobalFailureRate();
        return ResponseEntity.ok(rate);
    }
    // timeLine
    @GetMapping("/{id}/historique")
    public List<HistoriquePanne> getHistorique(@PathVariable Long id) {
        return historiqueService.getHistoriqueByEquipement(id);
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<EquipementEnPanne> updateStatut(@PathVariable Long id, @RequestBody Map<String, String> body) {
        EquipementEnPanne equipement = equipementRepo.findById(id).orElse(null);
        if (equipement == null) return ResponseEntity.notFound().build();

        String nouveauStatut = body.get("statut");
        String commentaireParDefaut = "Statut mis à jour par l'admin";
        String acteur = body.getOrDefault("acteur", "Admin");

        String ancienStatut = equipement.getStatut();
        equipement.setStatut(nouveauStatut);
        EquipementEnPanne updatedEquipement = equipementRepo.save(equipement);

        String commentaireTimeline = "";
        switch (nouveauStatut) {
            case "SIGNALÉ":
                commentaireTimeline = "Le statut a été réinitialisé à Signalé.";
                break;
            case "TRIAGE":
                commentaireTimeline = "Le ticket a été trié et est en attente d'assignation.";
                break;
            case "ASSIGNÉ":
                commentaireTimeline = "Le technicien a été assigné à la panne.";
                break;
            case "EN_COURS":
                commentaireTimeline = "Le diagnostic et l'intervention sur la panne ont commencé.";
                break;
            case "RÉSOLU":
                commentaireTimeline = "La panne a été résolue et vérifiée.";
                break;
            case "FERMÉ":
                commentaireTimeline = "Le ticket de panne a été fermé.";
                break;
            default:
                commentaireTimeline = "Statut mis à jour de '" + ancienStatut + "' à '" + nouveauStatut + "'.";
                break;
        }
        historiqueService.ajouterEtape(updatedEquipement, nouveauStatut, commentaireTimeline, acteur);
        return ResponseEntity.ok(updatedEquipement);
    }
}