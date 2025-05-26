package tn.esprit.equipementservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.equipementservice.entities.HistoriqueMouvement;

import java.util.List;

public interface HistoriqueMouvementRepository extends JpaRepository<HistoriqueMouvement,Long> {
    public List<HistoriqueMouvement> findByEquipement_IdEquipement(Long idEquipement);

    void deleteByEquipement_IdEquipement(Long idEquipement);

}
