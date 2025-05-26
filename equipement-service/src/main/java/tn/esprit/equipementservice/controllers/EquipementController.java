package tn.esprit.equipementservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.equipementservice.DTO.ListeLogicielDTO;
import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.services.IEquipement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/equipements")
@CrossOrigin(origins = "*")
public class EquipementController {

    private final IEquipement iEquipement;

    @Autowired
    public EquipementController(IEquipement iEquipement) {
        this.iEquipement = iEquipement;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}/logiciels")
    public ResponseEntity<List<ListeLogicielDTO>> getLogiciels(@PathVariable("id") Long id) {
        List<ListeLogicielDTO> logiciels = iEquipement.getLogicielsParEquipement(id);
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(logiciels);
    }

    @GetMapping("/agent/{matricule}")
    public ResponseEntity<List<EquipementInformatique>> getEquipementsByAgent(@PathVariable int matricule) {
        List<EquipementInformatique> equipements = iEquipement.getEquipementsByMatricule(matricule);
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(equipements);
    }

    @PostMapping
    public ResponseEntity<EquipementInformatique> ajouterEquipement(@RequestBody EquipementInformatique equipement) {
        System.out.println("Received equipement: " + equipement);
        EquipementInformatique savedEquipement = iEquipement.ajouterEquipement(equipement);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipement);
    }


    @GetMapping
    public ResponseEntity<List<EquipementInformatique>> getAllEquipements() {
        List<EquipementInformatique> equipements = iEquipement.listeEquipements();
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(equipements);
    }

    @GetMapping("/{numeroPatrimoine}")
    public ResponseEntity<EquipementInformatique> getEquipementById(@PathVariable Long numeroPatrimoine) {
        Optional<EquipementInformatique> equipement = iEquipement.getEquipementById(numeroPatrimoine);
        return equipement.map(e -> ResponseEntity.ok().body(e))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping(value = "/update/{numeroPatrimoine}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EquipementInformatique> updateEquipement(
            @PathVariable Long numeroPatrimoine, @RequestBody EquipementInformatique equipement) {
        EquipementInformatique updatedEquipement = iEquipement.updateEquipement(numeroPatrimoine, equipement);
        if (updatedEquipement != null) {
            return ResponseEntity.ok().body(updatedEquipement);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{numeroPatrimoine}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long numeroPatrimoine) {
        iEquipement.deleteEquipement(numeroPatrimoine);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{numeroSerie}/qr-code")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable String numeroSerie) {
        try {
            BufferedImage qrCodeImage = iEquipement.genererQRCode(numeroSerie);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "PNG", baos);
            byte[] qrCodeBytes = baos.toByteArray();

            return ResponseEntity.ok()
                    .header("Content-Type", "image/png")
                    .body(qrCodeBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{equipementId}/declare-panne")
    public ResponseEntity<EquipementEnPanne> declarePanne(@PathVariable Long equipementId, @RequestBody String descriptionPanne) {
        EquipementEnPanne panne = iEquipement.declarePanne(equipementId, descriptionPanne);
        return ResponseEntity.status(HttpStatus.CREATED).body(panne);
    }

    @GetMapping("/{equipementId}/pannes")
    public ResponseEntity<List<EquipementEnPanne>> getPannesByEquipement(@PathVariable Long equipementId) {
        List<EquipementEnPanne> pannes = iEquipement.getPannesByEquipement(equipementId);
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(pannes);
    }



    @GetMapping("/modele/{modele}")
    public ResponseEntity<List<EquipementInformatique>> getEquipementsByModele(@PathVariable String modele) {
        List<EquipementInformatique> equipements = iEquipement.getEquipementsByModele(modele);
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(equipements);
    }
    @GetMapping("/total-equipment")
    public long getTotalEquipement() {
        return iEquipement.getTotalEquipement();
    }

    @GetMapping("/active-equipment")
    public long getActiveEquipement() {
        return iEquipement.getActiveEquipement();
    }

    @GetMapping("/maintenance-equipment")
    public long getMaintenanceEquipement() {
        return iEquipement.getMaintenanceEquipement();
    }

    @GetMapping("/inactive-equipment")
    public long getInactiveEquipement() {
        return iEquipement.getInactiveEquipement();
    }
    // Statistiques mensuelles par état pour une année donnée
    @GetMapping("/stats/monthly-status/{year}")
    public ResponseEntity<Map<String, Map<String, Long>>> getMonthlyStatusByYear(@PathVariable int year) {
        return ResponseEntity.ok(iEquipement.getMonthlyStatusByYear(year));
    }

    // Nombre d’équipements par type
    @GetMapping("/stats/count-by-type")
    public ResponseEntity<Map<String, Long>> getEquipementCountByType() {
        return ResponseEntity.ok(iEquipement.getEquipementCountByType());
    }

    // Taux de panne par marque
    @GetMapping("/stats/failure-rate-by-brand")
    public ResponseEntity<Map<String, Double>> getFailureRateByBrand() {
        return ResponseEntity.ok(iEquipement.getFailureRateByBrand());
    }

    // Nombre d’équipements par localisation
    @GetMapping("/stats/count-by-location")
    public ResponseEntity<Map<String, Long>> getEquipementCountByLocation() {
        return ResponseEntity.ok(iEquipement.getEquipementCountByLocation());
    }

    // Âge moyen des équipements par type
    @GetMapping("/stats/average-age-by-type")
    public ResponseEntity<Map<String, Double>> getAverageAgeByType() {
        return ResponseEntity.ok(iEquipement.getAverageAgeByType());
    }

    // Tendance d’acquisition par mois pour une année donnée
    @GetMapping("/stats/acquisition-trend/{year}")
    public ResponseEntity<Map<String, Long>> getAcquisitionTrend(@PathVariable int year) {
        return ResponseEntity.ok(iEquipement.getAcquisitionTrend(year));
    }
    @GetMapping("/filtered")
    public ResponseEntity<List<EquipementInformatique>> getEquipementsByFilters(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer year) {

        List<EquipementInformatique> equipements = iEquipement.getEquipementsByFilters(location, type, year);
        return ResponseEntity.ok().body(equipements);
    }


}
