package tn.esprit.agentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.agentservice.entities.AgentNavigationAerienne;
import tn.esprit.agentservice.entities.ListeEquipementsParAgent;

import java.util.List;

@Repository
public interface AgentNavigationAerienneRepository extends JpaRepository<AgentNavigationAerienne,Long> {

}
