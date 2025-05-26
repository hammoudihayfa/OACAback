package tn.esprit.maintenanceservice.services;

import jakarta.servlet.http.HttpServletResponse;
import tn.esprit.maintenanceservice.entities.ProgrammeMaintenance;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProgrammeMaintenance {
    ProgrammeMaintenance ajouterProgrammeMaintenance(ProgrammeMaintenance programmeMaintenance);
    List<ProgrammeMaintenance> getAllProgrammesMaintenance();
    Optional<ProgrammeMaintenance> getProgrammeMaintenanceById(Long idProgrammeMaintenance);
    ProgrammeMaintenance updateProgrammeMaintenance(Long idProgrammeMaintenance, ProgrammeMaintenance programmeMaintenance);
    void deleteProgrammeMaintenance(Long idProgrammeMaintenance);
    List<ProgrammeMaintenance> getProgrammesMaintenanceByResponsable(String responsable);
    void exportProgrammesToCSV(HttpServletResponse response) throws IOException;
}
