package tn.esprit.maintenanceservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.maintenanceservice.entities.Rapport;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface RapportRepository extends JpaRepository<Rapport,Long> {
    List<Rapport> findByProgramme_IdProgrammeMaintenance(Long programmeId);
    List<Rapport> findByDateCreationBetween(LocalDate startDate, LocalDate endDate);


}
