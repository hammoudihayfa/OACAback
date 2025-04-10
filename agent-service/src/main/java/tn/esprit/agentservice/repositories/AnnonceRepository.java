package tn.esprit.agentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.agentservice.entities.Annonce;

import java.util.List;
@Repository
public interface AnnonceRepository extends JpaRepository<Annonce,Long> {
    List<Annonce> findByAgentMatricule(Long matricule);

}
