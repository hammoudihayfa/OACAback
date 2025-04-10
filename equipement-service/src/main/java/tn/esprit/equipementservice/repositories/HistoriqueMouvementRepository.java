package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.equipementservice.entities.HistoriqueMouvement;

import java.util.List;

public interface HistoriqueMouvementRepository extends JpaRepository<HistoriqueMouvement,Long> {
    public List<HistoriqueMouvement> findByEquipementNumeroPatrimoine(Long numeroPatrimoine);

}
