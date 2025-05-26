package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.equipementservice.entities.EquipementEnPanne;

import java.util.List;

@Repository
public interface EquipementEnPanneRepository extends JpaRepository<EquipementEnPanne, Long> {
    List<EquipementEnPanne> findByEquipement_IdEquipement(Long equipementId);
    void deleteByEquipement_IdEquipement(Long equipementId);
}
