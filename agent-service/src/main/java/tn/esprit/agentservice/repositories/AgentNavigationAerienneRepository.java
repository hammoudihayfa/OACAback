package tn.esprit.agentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.agentservice.entities.AgentNavigationAerienne;


import java.util.List;

public interface AgentNavigationAerienneRepository extends JpaRepository<AgentNavigationAerienne,Long> {
    List<AgentNavigationAerienne> findByNom(String nom);


}
