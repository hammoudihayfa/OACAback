package tn.esprit.agentservice.services;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import tn.esprit.agentservice.DOT.EquipementClient;
import tn.esprit.agentservice.DOT.EquipementDTO;
import tn.esprit.agentservice.entities.AgentNavigationAerienne;
import tn.esprit.agentservice.entities.ListeEquipementsParAgent;
import tn.esprit.agentservice.repositories.AgentNavigationAerienneRepository;
import tn.esprit.agentservice.repositories.ListeEquipementRepository;


import java.util.List;
import java.util.Optional;

@Service
public class AgentNavigationAerienneService implements IAgentNavigationAerienne {
    @Autowired
    private AgentNavigationAerienneRepository agentRepository;
    @Autowired
    private ListeEquipementRepository equipementRepository;
    private final EquipementClient equipementClient;
    private static final Logger logger = LoggerFactory.getLogger(AgentNavigationAerienneService.class);

    public AgentNavigationAerienneService(EquipementClient equipementClient) {
        this.equipementClient = equipementClient;
    }


    public List<EquipementDTO> getEquipementsByAgent(int matricule) {
        logger.info("Fetching equipments for agent: {}", matricule);
        List<EquipementDTO> equipements = equipementClient.getEquipementsByAgent(matricule);
        logger.info("Equipments fetched: {}", equipements);
        return equipements;
    }


    public AgentNavigationAerienne addAgent(AgentNavigationAerienne agent) {

        return agentRepository.save(agent);
    }


    public List<AgentNavigationAerienne> getAllAgents() {
        return agentRepository.findAll();
    }


    public Optional<AgentNavigationAerienne> getAgentById(Long matricule) {
        return agentRepository.findById(matricule);
    }


    public AgentNavigationAerienne updateAgent(Long matricule, AgentNavigationAerienne agent) {
        try {
            return agentRepository.findById(matricule)
                    .map(existingAgent -> {
                        existingAgent.setNom(agent.getNom());
                        existingAgent.setPrenom(agent.getPrenom());
                        existingAgent.setUnite(agent.getUnite());
                        existingAgent.setFonction(agent.getFonction());
                        existingAgent.setEmail(agent.getEmail());
                        existingAgent.setCin(agent.getCin());
                        return agentRepository.save(existingAgent);
                    })
                    .orElseThrow(() -> new RuntimeException("Agent avec matricule " + matricule + " introuvable."));
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("Conflit de modification : l'agent a été mis à jour par un autre utilisateur. Rechargez les données.");
        }
    }


    public void deleteAgent(Long matricule) {
        agentRepository.deleteById(matricule);
    }


    public void affecterEquipementAAgent(Long matricule, Long idEquipement) {
        Optional<AgentNavigationAerienne> agent = agentRepository.findById(matricule);
        Optional<ListeEquipementsParAgent> equipement = equipementRepository.findById(idEquipement);
        if (agent.isPresent() && equipement.isPresent()) {
            ListeEquipementsParAgent e = equipement.get();
            e.setUtilisateur(String.valueOf(matricule));
            equipementRepository.save(e);
        }
    }


    public List<ListeEquipementsParAgent> getEquipementsAffectes(Long matricule) {
        return equipementRepository.findByUtilisateur(String.valueOf(matricule));
    }


    public List<AgentNavigationAerienne> getAgentsByNom(String nom) {
        return agentRepository.findByNom(nom);
    }
}
