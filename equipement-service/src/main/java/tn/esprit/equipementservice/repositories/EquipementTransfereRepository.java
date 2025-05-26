package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.equipementservice.entities.EquipementTransfere;

import java.util.List;

@Repository
public interface EquipementTransfereRepository extends JpaRepository<EquipementTransfere,Long> {
    List<EquipementTransfere> findByEquipement_IdEquipement(Long idEquipement);

}
