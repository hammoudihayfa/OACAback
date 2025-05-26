package tn.esprit.equipementservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.entities.HistoriqueMouvement;
import tn.esprit.equipementservice.services.HistoriqueMouvementService;

import java.util.List;

@RestController
@RequestMapping("/historique-mouvements")
@CrossOrigin(origins = "*")
public class HistoriqueMouvementController {

    @Autowired
    private HistoriqueMouvementService mouvementService;

    @GetMapping
    public ResponseEntity<List<HistoriqueMouvement>> getAllMouvements() {
        return new ResponseEntity<>(mouvementService.getAllMouvements(), HttpStatus.OK);
    }

    @GetMapping("/equipement/{equipementId}")
    public ResponseEntity<List<HistoriqueMouvement>> getMouvementsByEquipementId(@PathVariable Long equipementId) {
        List<HistoriqueMouvement> mouvements = mouvementService.getMouvementsByEquipementId(equipementId);
        return new ResponseEntity<>(mouvements, HttpStatus.OK);
    }

    @GetMapping("/agent/{agentName}")
    public ResponseEntity<List<HistoriqueMouvement>> getMouvementsByAgentName(@PathVariable String agentName) {
        List<HistoriqueMouvement> mouvements = mouvementService.getMouvementsByUtilisateur(agentName);
        return new ResponseEntity<>(mouvements, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<HistoriqueMouvement>> filterMouvements(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "client", required = false) String client,
            @RequestParam(value = "patrimony", required = false) String patrimony) {
        return new ResponseEntity<>(mouvementService.filterMouvements(date, client, patrimony), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<HistoriqueMouvement>> searchMouvements(@RequestParam("searchTerm") String searchTerm) {
        return new ResponseEntity<>(mouvementService.searchMouvements(searchTerm), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<HistoriqueMouvement> addMouvement(@RequestBody HistoriqueMouvement mouvement) {
        HistoriqueMouvement saved = mouvementService.addMouvement(mouvement);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}