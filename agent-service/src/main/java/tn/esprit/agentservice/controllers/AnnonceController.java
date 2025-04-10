package tn.esprit.agentservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.agentservice.entities.Annonce;
import tn.esprit.agentservice.services.AnnonceService;

import java.util.List;

@RestController
@RequestMapping("/annonces")

public class AnnonceController {
    @Autowired
    private AnnonceService annonceService;

    @PostMapping
    public ResponseEntity<Annonce> createAnnonce(@RequestBody Annonce annonce) {
        return new ResponseEntity<>(annonceService.addAnnonce(annonce), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Annonce> getAllAnnonces() {
        return annonceService.getAllAnnonces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Annonce> getAnnonce(@PathVariable Long id) {
        Annonce annonce = annonceService.getAnnonceById(id);
        return annonce != null ? ResponseEntity.ok(annonce) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable Long id) {
        annonceService.deleteAnnonce(id);
        return ResponseEntity.noContent().build();
    }
}
