package tn.esprit.agentservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import tn.esprit.agentservice.DOT.EquipementClient;
import tn.esprit.agentservice.DOT.EquipementDTO;
import tn.esprit.agentservice.entities.AgentNavigationAerienne;
import tn.esprit.agentservice.entities.ListeEquipementsParAgent;
import tn.esprit.agentservice.repositories.AgentNavigationAerienneRepository;
import tn.esprit.agentservice.repositories.ListeEquipementRepository;
import tn.esprit.agentservice.services.AgentNavigationAerienneService;

class AgentNavigationAerienneServiceTest {

    @Mock
    private AgentNavigationAerienneRepository agentRepository;

    @Mock
    private ListeEquipementRepository equipementRepository;

    @Mock
    private EquipementClient equipementClient;

    @InjectMocks
    private AgentNavigationAerienneService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEquipementsByAgent() {
        int matricule = 123;
        List<EquipementDTO> equipements = List.of(new EquipementDTO());
        when(equipementClient.getEquipementsByAgent(matricule)).thenReturn(equipements);

        List<EquipementDTO> result = service.getEquipementsByAgent(matricule);

        assertEquals(equipements, result);
        verify(equipementClient, times(1)).getEquipementsByAgent(matricule);
    }

    @Test
    void testAddAgent() {
        AgentNavigationAerienne agent = new AgentNavigationAerienne();
        agent.setNom("Dupont");

        when(agentRepository.save(agent)).thenReturn(agent);

        AgentNavigationAerienne result = service.addAgent(agent);

        assertEquals(agent, result);
        verify(agentRepository, times(1)).save(agent);
    }

    @Test
    void testGetAllAgents() {
        List<AgentNavigationAerienne> agents = List.of(new AgentNavigationAerienne(), new AgentNavigationAerienne());
        when(agentRepository.findAll()).thenReturn(agents);

        List<AgentNavigationAerienne> result = service.getAllAgents();

        assertEquals(2, result.size());
        verify(agentRepository, times(1)).findAll();
    }

    @Test
    void testGetAgentById_Found() {
        Long matricule = 1L;
        AgentNavigationAerienne agent = new AgentNavigationAerienne();
        agent.setMatricule(matricule);

        when(agentRepository.findById(matricule)).thenReturn(Optional.of(agent));

        Optional<AgentNavigationAerienne> result = service.getAgentById(matricule);

        assertTrue(result.isPresent());
        assertEquals(matricule, result.get().getMatricule());
    }

    @Test
    void testGetAgentById_NotFound() {
        Long matricule = 1L;
        when(agentRepository.findById(matricule)).thenReturn(Optional.empty());

        Optional<AgentNavigationAerienne> result = service.getAgentById(matricule);

        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateAgent_Success() {
        Long matricule = 1L;
        AgentNavigationAerienne existingAgent = new AgentNavigationAerienne();
        existingAgent.setMatricule(matricule);
        existingAgent.setNom("Old");

        AgentNavigationAerienne updatedAgent = new AgentNavigationAerienne();
        updatedAgent.setNom("New");
        updatedAgent.setPrenom("Prenom");
        updatedAgent.setUnite("Unite");
        updatedAgent.setFonction("Fonction");
        updatedAgent.setEmail("email@example.com");
        updatedAgent.setCin(123456);

        when(agentRepository.findById(matricule)).thenReturn(Optional.of(existingAgent));
        when(agentRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        AgentNavigationAerienne result = service.updateAgent(matricule, updatedAgent);

        assertEquals("New", result.getNom());
        assertEquals("Prenom", result.getPrenom());
        verify(agentRepository, times(1)).findById(matricule);
        verify(agentRepository, times(1)).save(existingAgent);
    }

    @Test
    void testUpdateAgent_NotFound() {
        Long matricule = 1L;
        AgentNavigationAerienne updatedAgent = new AgentNavigationAerienne();

        when(agentRepository.findById(matricule)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.updateAgent(matricule, updatedAgent);
        });

        assertEquals("Agent avec matricule 1 introuvable.", ex.getMessage());
        verify(agentRepository, times(1)).findById(matricule);
        verify(agentRepository, never()).save(any());
    }

    @Test
    void testUpdateAgent_OptimisticLockingFailure() {
        Long matricule = 1L;
        AgentNavigationAerienne updatedAgent = new AgentNavigationAerienne();

        when(agentRepository.findById(matricule)).thenThrow(new ObjectOptimisticLockingFailureException(AgentNavigationAerienne.class, matricule));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.updateAgent(matricule, updatedAgent);
        });

        assertTrue(ex.getMessage().contains("Conflit de modification"));
    }

    @Test
    void testDeleteAgent() {
        Long matricule = 1L;
        doNothing().when(agentRepository).deleteById(matricule);

        service.deleteAgent(matricule);

        verify(agentRepository, times(1)).deleteById(matricule);
    }


    @Test
    void testGetEquipementsAffectes() {
        Long matricule = 1L;
        List<ListeEquipementsParAgent> liste = List.of(new ListeEquipementsParAgent(), new ListeEquipementsParAgent());

        when(equipementRepository.findByUtilisateur(String.valueOf(matricule))).thenReturn(liste);

        List<ListeEquipementsParAgent> result = service.getEquipementsAffectes(matricule);

        assertEquals(2, result.size());
        verify(equipementRepository, times(1)).findByUtilisateur(String.valueOf(matricule));
    }


}
