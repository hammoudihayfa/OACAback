package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.equipementservice.entities.EquipementEnService;
@Repository
public interface EquipementEnServiceRepository extends JpaRepository<EquipementEnService,Long> {
}
