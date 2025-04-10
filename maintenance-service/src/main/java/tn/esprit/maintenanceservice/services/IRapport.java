package tn.esprit.maintenanceservice.services;

import tn.esprit.maintenanceservice.entities.Rapport;

import java.time.LocalDate;
import java.util.List;

public interface IRapport {
     List<Rapport> getAllRapports() ;
     Rapport getRapportById(Long idRapport) ;
     Rapport addRapport(Rapport rapport) ;
     void deleteRapport(Long idRapport) ;
     List<Rapport> getRapportsByProgramme(Long idProgrammeMaintenance) ;
     List<Rapport> getRapportsByDateCreationBetween(LocalDate startDate, LocalDate endDate);
}
