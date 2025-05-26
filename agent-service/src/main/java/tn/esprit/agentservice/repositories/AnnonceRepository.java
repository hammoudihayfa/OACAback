package tn.esprit.agentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.agentservice.entities.Annonce;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce,Long> {
    List<Annonce> findByAgentMatricule(Long matricule);

}
