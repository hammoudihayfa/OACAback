package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.equipementservice.entities.HistoriquePanne;

import java.util.List;

public interface HistoriquePanneRepository extends JpaRepository<HistoriquePanne, Long> {
    List<HistoriquePanne> findByEquipementPanne_IdEquipementEnPanneOrderByDateAsc(Long idEquipementEnPanne);
}

