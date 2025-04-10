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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/programme-maintenance")
public class ProgrammeMaintenanceController {

    @Autowired
    private IProgrammeMaintenance programmeMaintenanceService;

    @PostMapping
    public ResponseEntity<ProgrammeMaintenance> createProgrammeMaintenance(@RequestBody ProgrammeMaintenance programmeMaintenance) {
        ProgrammeMaintenance createdProgramme = programmeMaintenanceService.createProgrammeMaintenance(programmeMaintenance);
        return new ResponseEntity<>(createdProgramme, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProgrammeMaintenance>> getAllProgrammesMaintenance() {
        List<ProgrammeMaintenance> programmes = programmeMaintenanceService.getAllProgrammesMaintenance();
        return new ResponseEntity<>(programmes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammeMaintenance> getProgrammeMaintenanceById(@PathVariable Long id) {
        Optional<ProgrammeMaintenance> programme = programmeMaintenanceService.getProgrammeMaintenanceById(id);
        return programme.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammeMaintenance> updateProgrammeMaintenance(
            @PathVariable Long id, @RequestBody ProgrammeMaintenance programmeMaintenance) {
        ProgrammeMaintenance updatedProgramme = programmeMaintenanceService.updateProgrammeMaintenance(id, programmeMaintenance);
        return updatedProgramme != null ? new ResponseEntity<>(updatedProgramme, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgrammeMaintenance(@PathVariable Long id) {
        try {
            programmeMaintenanceService.deleteProgrammeMaintenance(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Avancée
    @GetMapping("/before/{date}")
    public ResponseEntity<List<ProgrammeMaintenance>> getProgrammesBeforeDate(@PathVariable String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(date);
            List<ProgrammeMaintenance> programmes = programmeMaintenanceService.getProgrammesByDateBefore(parsedDate);
            return new ResponseEntity<>(programmes, HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/responsable/{responsable}")
    public ResponseEntity<List<ProgrammeMaintenance>> getProgrammesByResponsable(@PathVariable String responsable) {
        List<ProgrammeMaintenance> programmes = programmeMaintenanceService.getProgrammesByResponsable(responsable);
        return new ResponseEntity<>(programmes, HttpStatus.OK);
    }

    @GetMapping("/count-between")
    public ResponseEntity<Long> countProgrammesExecutedBetween(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            long count = programmeMaintenanceService.countProgrammesExecutedBetween(start, end);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/export-csv")
    public void exportProgrammesToCSV(HttpServletResponse response) throws IOException {
        programmeMaintenanceService.exportProgrammesToCSV(response);
    }

}
