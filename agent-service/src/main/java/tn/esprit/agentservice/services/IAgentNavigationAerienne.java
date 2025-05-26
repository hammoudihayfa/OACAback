package tn.esprit.agentservice.services;

import tn.esprit.agentservice.DOT.EquipementClient;
import tn.esprit.agentservice.DOT.EquipementDTO;
import tn.esprit.agentservice.entities.AgentNavigationAerienne;
import tn.esprit.agentservice.entities.ListeEquipementsParAgent;

import java.util.List;
import java.util.Optional;

public interface IAgentNavigationAerienne {
    List<EquipementDTO> getEquipementsByAgent(int matricule);
    AgentNavigationAerienne addAgent(AgentNavigationAerienne agent);
    List<AgentNavigationAerienne> getAllAgents();
    Optional<AgentNavigationAerienne> getAgentById(Long matricule);
    AgentNavigationAerienne updateAgent(Long matricule, AgentNavigationAerienne agent);
    void deleteAgent(Long matricule);
    void affecterEquipementAAgent(Long matricule, Long idEquipement);

    List<ListeEquipementsParAgent> getEquipementsAffectes(Long matricule);
    List<AgentNavigationAerienne> getAgentsByNom(String nom);
}
