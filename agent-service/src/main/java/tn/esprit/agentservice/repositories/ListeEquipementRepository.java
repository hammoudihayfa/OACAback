package tn.esprit.agentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.agentservice.entities.ListeEquipementsParAgent;

import java.util.List;

public interface ListeEquipementRepository extends JpaRepository<ListeEquipementsParAgent,Long> {
    List<ListeEquipementsParAgent> findByUtilisateur(String utilisateur);
    List<ListeEquipementsParAgent> findByStatutEquipement(String statutEquipement);
    List<ListeEquipementsParAgent> findByMarque(String marque);


}
