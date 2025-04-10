package tn.esprit.maintenanceservice.services;

import jakarta.servlet.http.HttpServletResponse;
import tn.esprit.maintenanceservice.entities.ProgrammeMaintenance;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IProgrammeMaintenance {
    ProgrammeMaintenance createProgrammeMaintenance(ProgrammeMaintenance programmeMaintenance);
    List<ProgrammeMaintenance> getAllProgrammesMaintenance();
    Optional<ProgrammeMaintenance> getProgrammeMaintenanceById(Long idProgrammeMaintenance);
    ProgrammeMaintenance updateProgrammeMaintenance(Long idProgrammeMaintenance, ProgrammeMaintenance programmeMaintenance);

    void deleteProgrammeMaintenance(Long idProgrammeMaintenance);
    List<ProgrammeMaintenance> getProgrammesByDateBefore(Date date);
    List<ProgrammeMaintenance> getProgrammesByResponsable(String responsable);
    long countProgrammesExecutedBetween(Date startDate, Date endDate);
    void exportProgrammesToCSV(HttpServletResponse response) throws IOException;
    Date getNextExecutionDate(ProgrammeMaintenance programme);
}
