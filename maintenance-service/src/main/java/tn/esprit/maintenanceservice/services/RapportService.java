package tn.esprit.maintenanceservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.maintenanceservice.entities.Rapport;
import tn.esprit.maintenanceservice.repositories.RapportRepository;

import java.time.LocalDate;
import java.util.List;
@Service
public class RapportService implements IRapport{
    @Autowired
    private RapportRepository rapportRepository;

    public List<Rapport> getAllRapports() {
        return rapportRepository.findAll();
    }

    public Rapport getRapportById(Long idRapport) {
        return rapportRepository.findById(idRapport).orElse(null);
    }

    public Rapport addRapport(Rapport rapport) {
        rapport.setDateCreation(LocalDate.now());
        return rapportRepository.save(rapport);
    }

    public void deleteRapport(Long idRapport) {
        rapportRepository.deleteById(idRapport);
    }

    public List<Rapport> getRapportsByProgramme(Long idProgrammeMaintenance) {
        return rapportRepository.findByProgramme_IdProgrammeMaintenance(idProgrammeMaintenance);
    }
    //Avancee
    public List<Rapport> getRapportsByDateCreationBetween(LocalDate startDate, LocalDate endDate) {
        return rapportRepository.findByDateCreationBetween(startDate, endDate);
    }

}
