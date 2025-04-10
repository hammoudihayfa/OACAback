package tn.esprit.agentservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.agentservice.DOT.EquipementDTO;
import tn.esprit.agentservice.entities.AgentNavigationAerienne;
import tn.esprit.agentservice.entities.ListeEquipementsParAgent;
import tn.esprit.agentservice.services.AgentNavigationAerienneService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agents")

public class AgentNavigationAerienneController {
    @Autowired
    private AgentNavigationAerienneService agentService;


    public AgentNavigationAerienneController(AgentNavigationAerienneService agentService) {
        this.agentService = agentService;
    }

    @GetMapping("/{matricule}/equipements-dto")
    public ResponseEntity<List<EquipementDTO>> getEquipementsByAgent(@PathVariable int matricule) {
        return ResponseEntity.ok(agentService.getEquipementsByAgent(matricule));
    }

    @PostMapping
    public ResponseEntity<AgentNavigationAerienne> addAgent(@RequestBody AgentNavigationAerienne agent) {
        AgentNavigationAerienne newAgent = agentService.addAgent(agent);
        return ResponseEntity.ok(newAgent);
    }

    @GetMapping
    public ResponseEntity<List<AgentNavigationAerienne>> getAllAgents() {
        List<AgentNavigationAerienne> agents = agentService.getAllAgents();
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/{matricule}")
    public ResponseEntity<AgentNavigationAerienne> getAgentById(@PathVariable Long matricule) {
        Optional<AgentNavigationAerienne> agent = agentService.getAgentById(matricule);
        return agent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<AgentNavigationAerienne> updateAgent(
            @PathVariable Long matricule,
            @RequestBody AgentNavigationAerienne agent) {
        try {
            AgentNavigationAerienne updatedAgent = agentService.updateAgent(matricule, agent);
            return ResponseEntity.ok(updatedAgent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{matricule}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long matricule) {
        agentService.deleteAgent(matricule);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{matricule}/affecter-equipement/{idEquipement}")
    public ResponseEntity<Void> affecterEquipement(@PathVariable Long matricule, @PathVariable Long idEquipement) {
        agentService.affecterEquipementAAgent(matricule, idEquipement);
        return ResponseEntity.ok().build();
    }

    // Récupérer les équipements affectés à un agent
    @GetMapping("/{matricule}/equipements")
    public ResponseEntity<List<ListeEquipementsParAgent>> getEquipementsAffectes(@PathVariable Long matricule) {
        return ResponseEntity.ok(agentService.getEquipementsAffectes(matricule));
    }
}
