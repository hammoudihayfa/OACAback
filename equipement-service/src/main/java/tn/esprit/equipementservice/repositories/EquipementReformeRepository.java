package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.equipementservice.entities.EquipementReforme;

import java.util.Optional;

@Repository
public interface EquipementReformeRepository extends JpaRepository<EquipementReforme, Long> {
    Optional<EquipementReforme> findByEquipementIdEquipement(Long idEquipement);
}
