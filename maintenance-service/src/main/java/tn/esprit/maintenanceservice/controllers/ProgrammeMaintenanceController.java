package tn.esprit.maintenanceservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.maintenanceservice.entities.ProgrammeMaintenance;
import tn.esprit.maintenanceservice.services.IProgrammeMaintenance;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/programme-maintenance")
@CrossOrigin(origins = "*")

public class ProgrammeMaintenanceController {

    private final IProgrammeMaintenance programmeMaintenanceService;

    public ProgrammeMaintenanceController(IProgrammeMaintenance programmeMaintenanceService) {
        this.programmeMaintenanceService = programmeMaintenanceService;
    }

    @PostMapping
    public ResponseEntity<ProgrammeMaintenance> createProgramme(@RequestBody ProgrammeMaintenance programme) {
        ProgrammeMaintenance created = programmeMaintenanceService.ajouterProgrammeMaintenance(programme);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<ProgrammeMaintenance>> getAllProgrammes() {
        List<ProgrammeMaintenance> programmes = programmeMaintenanceService.getAllProgrammesMaintenance();
        return ResponseEntity.ok(programmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammeMaintenance> getProgrammeById(@PathVariable Long id) {
        Optional<ProgrammeMaintenance> programme = programmeMaintenanceService.getProgrammeMaintenanceById(id);
        return programme.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammeMaintenance> updateProgramme(
            @PathVariable Long id,
            @RequestBody ProgrammeMaintenance updatedProgramme) {
        ProgrammeMaintenance updated = programmeMaintenanceService.updateProgrammeMaintenance(id, updatedProgramme);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgramme(@PathVariable Long id) {
        programmeMaintenanceService.deleteProgrammeMaintenance(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/responsable/{responsable}")
    public ResponseEntity<List<ProgrammeMaintenance>> getByResponsable(@PathVariable String responsable) {
        List<ProgrammeMaintenance> programmes = programmeMaintenanceService.getProgrammesMaintenanceByResponsable(responsable);
        return ResponseEntity.ok(programmes);
    }

    @GetMapping("/export/csv")
    public void exportCSV(HttpServletResponse response) {
        try {
            programmeMaintenanceService.exportProgrammesToCSV(response);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'exportation CSV : " + e.getMessage());
        }
    }
    @GetMapping("/calendar")
    public ResponseEntity<List<Map<String, Object>>> getCalendarEvents() {
        List<ProgrammeMaintenance> programmes = programmeMaintenanceService.getAllProgrammesMaintenance();

        List<Map<String, Object>> events = programmes.stream().map(p -> {
            Map<String, Object> event = new HashMap<>();
            event.put("id", p.getIdProgrammeMaintenance());
            event.put("title", p.getPlanAction());
            event.put("start", p.getStartDate());
            event.put("end", p.getStartDate().plusDays(p.getEstimatedDurationDays() != null ? p.getEstimatedDurationDays() : 1));
            return event;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(events);
    }

}
