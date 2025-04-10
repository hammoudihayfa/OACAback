package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.equipementservice.entities.EquipementRepartition;
@Repository
public interface EquipementRepartitionRepository extends JpaRepository<EquipementRepartition,Long> {
}
