package tn.esprit.logicielservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.logicielservice.entities.ListeLogiciel;

import java.util.List;

@Repository
public interface ListeLogicielRepository extends JpaRepository<ListeLogiciel,Long> {
    List<ListeLogiciel> findByEquipementId(Long equipementId);
    List<ListeLogiciel> findByNomLogicielContainingIgnoreCase(String nom);
    List<ListeLogiciel> findByVersionContainingIgnoreCase(String version);

}
