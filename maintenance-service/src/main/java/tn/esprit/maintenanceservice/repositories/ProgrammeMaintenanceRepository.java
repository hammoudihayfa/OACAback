package tn.esprit.maintenanceservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.maintenanceservice.entities.ProgrammeMaintenance;

import java.util.Date;
import java.util.List;

@Repository
public interface ProgrammeMaintenanceRepository extends JpaRepository<ProgrammeMaintenance,Long> {
    public List<ProgrammeMaintenance> findByDateDerniereExecutionBefore(Date date);
    List<ProgrammeMaintenance> findByResponsable(String responsable);
    long countByDateDerniereExecutionBetween(Date startDate, Date endDate);



}
