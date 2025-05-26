package tn.esprit.logicielservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.logicielservice.entities.ListeLogiciel;
import tn.esprit.logicielservice.services.IListeLogiciel;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/logiciels-service")
public class ListeLogicielController {
    @Autowired
    private IListeLogiciel listeLogicielService;


    @PostMapping
    public ResponseEntity<ListeLogiciel> createLogiciel(@RequestBody ListeLogiciel logiciel) {
        ListeLogiciel createdLogiciel = listeLogicielService.createLogiciel(logiciel);
        return new ResponseEntity<>(createdLogiciel, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ListeLogiciel>> getAllLogiciels() {
        List<ListeLogiciel> logiciels = listeLogicielService.getAllLogiciels();
        return new ResponseEntity<>(logiciels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListeLogiciel> getLogicielById(@PathVariable Long id) {
        Optional<ListeLogiciel> logiciel = listeLogicielService.getLogicielById(id);
        if (logiciel.isPresent()) {
            return new ResponseEntity<>(logiciel.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListeLogiciel> updateLogiciel(@PathVariable Long id, @RequestBody ListeLogiciel logiciel) {
        ListeLogiciel updatedLogiciel = listeLogicielService.updateLogiciel(id, logiciel);
        if (updatedLogiciel != null) {
            return new ResponseEntity<>(updatedLogiciel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogiciel(@PathVariable Long id) {
        if (listeLogicielService.getLogicielById(id).isPresent()) {
            listeLogicielService.deleteLogiciel(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search/nom")
    public List<ListeLogiciel> searchByNomLogiciel(@RequestParam String nom) {
        return listeLogicielService.searchByNomLogiciel(nom);
    }

    @GetMapping("/search/version")
    public List<ListeLogiciel> searchByVersion(@RequestParam String version) {
        return listeLogicielService.searchByVersion(version);
    }


}
