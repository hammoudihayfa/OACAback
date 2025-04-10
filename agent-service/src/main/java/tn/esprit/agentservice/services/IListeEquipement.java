package tn.esprit.agentservice.services;

import tn.esprit.agentservice.entities.ListeEquipementsParAgent;

import java.util.List;
import java.util.Optional;

public interface IListeEquipement {
    ListeEquipementsParAgent ajouterEquipementParAgent(ListeEquipementsParAgent equipement);

    List<ListeEquipementsParAgent> getEquipementsParAgent();

    Optional<ListeEquipementsParAgent> getEquipementParAgentById(Long idEquipementParAgent);

    ListeEquipementsParAgent updateEquipementParAgent(Long idEquipementParAgent, ListeEquipementsParAgent equipement);

    void deleteEquipementParAgent(Long idEquipementParAgent);

    void affecterAgentAEquipement(Long idEquipement, Long matricule);

    // Trouver les équipements disponibles (non affectés)
    List<ListeEquipementsParAgent> getEquipementsDisponibles();

    ListeEquipementsParAgent changerStatutEquipement(Long idEquipement, String nouveauStatut);
}
