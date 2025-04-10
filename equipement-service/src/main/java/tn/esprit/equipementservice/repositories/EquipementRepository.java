package tn.esprit.equipementservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.equipementservice.entities.EquipementInformatique;
@Repository
public interface EquipementRepository extends JpaRepository<EquipementInformatique,Long> {
    EquipementInformatique findByMatricule(int matricule);
}
