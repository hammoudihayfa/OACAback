package tn.esprit.equipementservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.DTO.ListeLogicielDTO;
import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.HistoriqueMouvement;
import tn.esprit.equipementservice.services.IEquipement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipements")

public class EquipementController {
@Autowired
    private final IEquipement iEquipement;
    @GetMapping("/{id}/logiciels")
    public List<ListeLogicielDTO> getLogiciels(@PathVariable("id") Long id) {
        return iEquipement.getLogicielsParEquipement(id);
    }

    public EquipementController(IEquipement iEquipement) {
        this.iEquipement = iEquipement;
    }
    @GetMapping("/agent/{matricule}")
    public List<EquipementInformatique> getEquipementsByAgent(@PathVariable int matricule) {
        return iEquipement.getEquipementsByMatricule(matricule);
    }

    @PostMapping
    public ResponseEntity<String> ajouterEquipement(@RequestBody EquipementInformatique equipement) {
        iEquipement.ajouterEquipement(equipement);
        return ResponseEntity.ok("Équipement ajouté avec succès");
    }

    @GetMapping
    public ResponseEntity<List<EquipementInformatique>> getAllEquipements() {
        List<EquipementInformatique> equipements = iEquipement.listeEquipements();
        return new ResponseEntity<>(equipements, HttpStatus.OK);
    }

    @GetMapping("/{numeroPatrimoine}")
    public ResponseEntity<EquipementInformatique> getEquipementById(@PathVariable Long numeroPatrimoine) {
        Optional<EquipementInformatique> equipement = iEquipement.getEquipementById(numeroPatrimoine);
        if (equipement.isPresent()) {
            return new ResponseEntity<>(equipement.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{numeroPatrimoine}")
    public ResponseEntity<EquipementInformatique> updateEquipement(
            @PathVariable Long numeroPatrimoine, @RequestBody EquipementInformatique equipement) {
        EquipementInformatique updatedEquipement = iEquipement.updateEquipement(numeroPatrimoine, equipement);
        if (updatedEquipement != null) {
            return new ResponseEntity<>(updatedEquipement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{numeroPatrimoine}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long numeroPatrimoine) {
        iEquipement.deleteEquipement(numeroPatrimoine);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{matricule}/qr-code")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable int matricule) {
        try {
            BufferedImage qrCodeImage = iEquipement.genererQRCode(matricule);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "PNG", baos);
            byte[] qrCodeBytes = baos.toByteArray();

            return ResponseEntity.ok()
                    .header("Content-Type", "image/png")
                    .body(qrCodeBytes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{equipementId}/declare-panne")
    public ResponseEntity<EquipementEnPanne> declarePanne(@PathVariable Long equipementId, @RequestBody String descriptionPanne) {
        EquipementEnPanne panne = iEquipement.declarePanne(equipementId, descriptionPanne);
        return new ResponseEntity<>(panne, HttpStatus.CREATED);
    }

    // Obtenir l'historique des pannes
    @GetMapping("/{equipementId}/pannes")
    public List<EquipementEnPanne> getPannesByEquipement(@PathVariable Long equipementId) {
        return iEquipement.getPannesByEquipement(equipementId);
    }

    // Ajouter un mouvement
    @PostMapping("/{equipementId}/mouvements")
    public ResponseEntity<HistoriqueMouvement> ajouterMouvement(@PathVariable Long equipementId, @RequestBody String typeMouvement) {
        HistoriqueMouvement mouvement = iEquipement.ajouterMouvement(equipementId, typeMouvement);
        return new ResponseEntity<>(mouvement, HttpStatus.CREATED);
    }

    // Obtenir l'historique des mouvements
    @GetMapping("/{equipementId}/mouvements")
    public List<HistoriqueMouvement> getHistoriqueMouvements(@PathVariable Long equipementId) {
        return iEquipement.getHistoriqueMouvements(equipementId);
    }
}
