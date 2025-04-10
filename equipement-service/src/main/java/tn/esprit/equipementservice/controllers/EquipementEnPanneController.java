package tn.esprit.equipementservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.DTO.EquipementMaintenanceDTO;
import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.services.EquipementMaintenanceService;
import tn.esprit.equipementservice.services.IEquipementEnPanne;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipements-en-panne")
public class EquipementEnPanneController {
    @Autowired
    private final IEquipementEnPanne iEquipementEnPanne;
    @Autowired
    private final EquipementMaintenanceService equipementMaintenanceService;

    public EquipementEnPanneController(IEquipementEnPanne iEquipementEnPanne, EquipementMaintenanceService equipementMaintenanceService) {
        this.iEquipementEnPanne = iEquipementEnPanne;
        this.equipementMaintenanceService = equipementMaintenanceService;
    }
    @GetMapping("/programme-maintenance/{idEquipement}")
    public ResponseEntity<EquipementMaintenanceDTO> getEquipementAvecMaintenance(@PathVariable Long idEquipement) {
        EquipementMaintenanceDTO dto = equipementMaintenanceService.getEquipementAvecMaintenance(idEquipement);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<String> ajouterEquipementEnPanne(@RequestBody EquipementEnPanne equipementEnPanne) {
        iEquipementEnPanne.ajouterEquipementEnPanne(equipementEnPanne);
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
}
