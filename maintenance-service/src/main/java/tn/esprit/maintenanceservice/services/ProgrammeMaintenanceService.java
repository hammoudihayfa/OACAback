package tn.esprit.maintenanceservice.services;

import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import tn.esprit.maintenanceservice.entities.FrequencyType;
import tn.esprit.maintenanceservice.entities.ProgrammeMaintenance;
import tn.esprit.maintenanceservice.repositories.ProgrammeMaintenanceRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeMaintenanceService implements IProgrammeMaintenance {

    private final ProgrammeMaintenanceRepository programmeMaintenanceRepository;

    public ProgrammeMaintenanceService(ProgrammeMaintenanceRepository programmeMaintenanceRepository) {
        this.programmeMaintenanceRepository = programmeMaintenanceRepository;
    }

    private LocalDate calculateNextDueDate(LocalDate startDate, FrequencyType frequencyType) {
        if (startDate == null || frequencyType == null) {
            return null;
        }

        return switch (frequencyType) {
            case DAILY -> startDate.plusDays(1);
            case WEEKLY -> startDate.plusWeeks(1);
            case MONTHLY -> startDate.plusMonths(1);
            case QUARTERLY -> startDate.plusMonths(3);
            case YEARLY -> startDate.plusYears(1);
            case ONE_TIME -> startDate;
        };
    }

    @Override
    public ProgrammeMaintenance ajouterProgrammeMaintenance(ProgrammeMaintenance programme) {
        if (programme.getStartDate() != null && programme.getFrequencyType() != null) {
            programme.setNextDueDate(calculateNextDueDate(programme.getStartDate(), programme.getFrequencyType()));
        }
        return programmeMaintenanceRepository.save(programme);
    }

    @Override
    public List<ProgrammeMaintenance> getAllProgrammesMaintenance() {
        return programmeMaintenanceRepository.findAll();
    }

    @Override
    public Optional<ProgrammeMaintenance> getProgrammeMaintenanceById(Long id) {
        return programmeMaintenanceRepository.findById(id);
    }

    @Override
    public ProgrammeMaintenance updateProgrammeMaintenance(Long id, ProgrammeMaintenance programmeDetails) {
        return programmeMaintenanceRepository.findById(id)
                .map(programme -> {
                    programme.setPlanAction(programmeDetails.getPlanAction());
                    programme.setProcedureMaintenance(programmeDetails.getProcedureMaintenance());
                    programme.setActionCorrective(programmeDetails.getActionCorrective());
                    programme.setObservation(programmeDetails.getObservation());
                    programme.setResponsable(programmeDetails.getResponsable());
                    programme.setEtudeSecurite(programmeDetails.getEtudeSecurite());
                    programme.setStartDate(programmeDetails.getStartDate());
                    programme.setFrequencyType(programmeDetails.getFrequencyType());
                    programme.setEstimatedDurationDays(programmeDetails.getEstimatedDurationDays());

                    if (programme.getStartDate() != null && programme.getFrequencyType() != null) {
                        programme.setNextDueDate(calculateNextDueDate(programme.getStartDate(), programme.getFrequencyType()));
                    } else {
                        programme.setNextDueDate(null);
                    }

                    return programmeMaintenanceRepository.save(programme);
                }).orElse(null);
    }

    @Override
    public void deleteProgrammeMaintenance(Long id) {
        programmeMaintenanceRepository.deleteById(id);
    }

    @Override
    public List<ProgrammeMaintenance> getProgrammesMaintenanceByResponsable(String responsable) {
        return programmeMaintenanceRepository.findByResponsableContainingIgnoreCase(responsable);
    }

    @Override
    public void exportProgrammesToCSV(HttpServletResponse response) throws IOException {
        List<ProgrammeMaintenance> programmes = programmeMaintenanceRepository.findAll();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=programmes.csv");

        CSVWriter writer = new CSVWriter(response.getWriter());
        writer.writeNext(new String[] {
                "ID",
                "Plan d'Action",
                "Procédure de Maintenance",
                "Action Corrective",
                "Observation",
                "Responsable",
                "Étude de Sécurité"
        });

        for (ProgrammeMaintenance programme : programmes) {
            writer.writeNext(new String[] {
                    String.valueOf(programme.getIdProgrammeMaintenance()),
                    programme.getPlanAction(),
                    programme.getProcedureMaintenance(),
                    programme.getActionCorrective(),
                    programme.getObservation(),
                    programme.getResponsable(),
                    programme.getEtudeSecurite()
            });
        }

        writer.close();
    }
}
