package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.equipementservice.entities.EquipementEnStock;
@Repository
public interface EquipementEnStockRepository extends JpaRepository<EquipementEnStock,Long> {
}
