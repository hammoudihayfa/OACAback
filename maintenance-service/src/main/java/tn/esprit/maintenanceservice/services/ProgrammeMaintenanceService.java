package tn.esprit.maintenanceservice.services;

import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.maintenanceservice.entities.ProgrammeMaintenance;
import tn.esprit.maintenanceservice.repositories.ProgrammeMaintenanceRepository;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeMaintenanceService implements IProgrammeMaintenance{
    @Autowired
    private ProgrammeMaintenanceRepository programmeMaintenanceRepository;
    @Override
    public ProgrammeMaintenance createProgrammeMaintenance(ProgrammeMaintenance programmeMaintenance) {
        return programmeMaintenanceRepository.save(programmeMaintenance);
    }

    @Override
    public List<ProgrammeMaintenance> getAllProgrammesMaintenance() {
        return programmeMaintenanceRepository.findAll();
    }

    @Override
    public Optional<ProgrammeMaintenance> getProgrammeMaintenanceById(Long idProgrammeMaintenance) {
        return programmeMaintenanceRepository.findById(idProgrammeMaintenance);
    }

    @Override
    public ProgrammeMaintenance updateProgrammeMaintenance(Long idProgrammeMaintenance, ProgrammeMaintenance programmeMaintenance) {
        if (programmeMaintenanceRepository.existsById(idProgrammeMaintenance)) {
            programmeMaintenance.setIdProgrammeMaintenance(idProgrammeMaintenance);
            return programmeMaintenanceRepository.save(programmeMaintenance);
        } else {
            return null;
        }
    }

    @Override
    public void deleteProgrammeMaintenance(Long idProgrammeMaintenance) {
        if (programmeMaintenanceRepository.existsById(idProgrammeMaintenance)) {
            programmeMaintenanceRepository.deleteById(idProgrammeMaintenance);
        } else {
            throw new RuntimeException("Programme de maintenance avec l'ID " + idProgrammeMaintenance + " n'existe pas.");
        }
    }
    // Avancee
    public List<ProgrammeMaintenance> getProgrammesByDateBefore(Date date) {
        return programmeMaintenanceRepository.findByDateDerniereExecutionBefore(date);
    }
    public List<ProgrammeMaintenance> getProgrammesByResponsable(String responsable) {
        return programmeMaintenanceRepository.findByResponsable(responsable);
    }
    public long countProgrammesExecutedBetween(Date startDate, Date endDate) {
        return programmeMaintenanceRepository.countByDateDerniereExecutionBetween(startDate, endDate);
    }
    public void exportProgrammesToCSV(HttpServletResponse response) throws IOException {
        List<ProgrammeMaintenance> programmes = programmeMaintenanceRepository.findAll();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=programmes.csv");

        CSVWriter writer = new CSVWriter(response.getWriter());
        writer.writeNext(new String[] { "ID", "Nom", "Fréquence", "Date Dernière Exécution" });

        for (ProgrammeMaintenance programme : programmes) {
            writer.writeNext(new String[] {
                    String.valueOf(programme.getIdProgrammeMaintenance()),
                    programme.getNomProgramme(),
                    programme.getFrequence(),
                    programme.getDateDerniereExecution().toString()
            });
        }

        writer.close();
    }

    public Date getNextExecutionDate(ProgrammeMaintenance programme) {
        // Exemple basique, selon la fréquence : "semaine", "mois", etc.
        Calendar cal = Calendar.getInstance();
        cal.setTime(programme.getDateDerniereExecution());

        switch (programme.getFrequence()) {
            case "semaine":
                cal.add(Calendar.WEEK_OF_YEAR, 1);
                break;
            case "mois":
                cal.add(Calendar.MONTH, 1);
                break;
            default:
                // Si aucune fréquence n'est précisée
                return programme.getDateDerniereExecution();
        }

        return cal.getTime();
    }



}
