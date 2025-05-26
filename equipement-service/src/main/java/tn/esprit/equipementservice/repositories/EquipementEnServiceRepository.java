package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.equipementservice.entities.EquipementEnService;

import java.util.Optional;

@Repository
public interface EquipementEnServiceRepository extends JpaRepository<EquipementEnService, Long> {
    Optional<EquipementEnService> findByEquipementIdEquipement(Long idEquipement);
}
