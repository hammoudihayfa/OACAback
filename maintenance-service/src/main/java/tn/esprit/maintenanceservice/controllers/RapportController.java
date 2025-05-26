package tn.esprit.maintenanceservice.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.maintenanceservice.entities.Rapport;
import tn.esprit.maintenanceservice.services.RapportService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rapports")
@CrossOrigin(origins = "*")

public class RapportController {

    @Autowired
    private RapportService rapportService;

    @PostMapping
    public ResponseEntity<Rapport> createRapport(@RequestBody Rapport rapport) {
        Rapport savedRapport = rapportService.addRapport(rapport);
        return ResponseEntity.status(201).body(savedRapport);
    }

    @GetMapping
    public List<Rapport> getAllRapports() {
        return rapportService.getAllRapports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rapport> getRapportById(@PathVariable Long id) {
        Rapport rapport = rapportService.getRapportById(id);
        return rapport != null ? ResponseEntity.ok(rapport) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRapport(@PathVariable Long id) {
        rapportService.deleteRapport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/programme/{idProgramme}")
    public List<Rapport> getRapportsByProgramme(@PathVariable Long idProgramme) {
        return rapportService.getRapportsByProgramme(idProgramme);
    }

    @GetMapping("/export-pdf/{idRapport}")
    public void exportRapportToPDF(@PathVariable Long idRapport, HttpServletResponse response) throws Exception {
        Rapport rapport = rapportService.getRapportById(idRapport);
        if (rapport == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Set PDF response header
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=rapport_" + idRapport + ".pdf");

        // Créer un document iText
        Document document = new Document();

        // Créer le PdfWriter et lier au flux de sortie HTTP
        PdfWriter.getInstance(document, response.getOutputStream());

        // Ouvrir le document pour l'écriture
        document.open();

        // Ajouter le contenu du rapport au PDF
        document.add(new Paragraph("Rapport ID: " + rapport.getIdRapport()));
        document.add(new Paragraph("Date de création: " + rapport.getDateCreation()));
        document.add(new Paragraph("Détails du rapport: " + rapport.getContenu())); // Use getContenu() instead of getDetails()

        // Fermer le document
        document.close();
    }


    // Filtrer les rapports entre deux dates
    @GetMapping("/date-between")
    public List<Rapport> getRapportsByDateCreationBetween(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            return rapportService.getRapportsByDateCreationBetween(start, end);
        } catch (Exception e) {
            return null;
        }
    }
}
