package tn.esprit.agentservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.agentservice.entities.ListeEquipementsParAgent;
import tn.esprit.agentservice.repositories.ListeEquipementRepository;
import tn.esprit.agentservice.services.ListeEquipementService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListeEquipementServiceTest {

    @Mock
    private ListeEquipementRepository listeEquipementRepository;

    @InjectMocks
    private ListeEquipementService listeEquipementService;

    private ListeEquipementsParAgent equipement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        equipement = new ListeEquipementsParAgent();
        equipement.setIdEquipementParAgent(1L);
        equipement.setMarque("Dell");
        equipement.setNature("Ordinateur");
        equipement.setStatutEquipement("DISPONIBLE");
        equipement.setUtilisateur(null);
        // compléter avec d'autres setters si besoin
    }

    @Test
    void testAjouterEquipementParAgent() {
        when(listeEquipementRepository.save(equipement)).thenReturn(equipement);

        ListeEquipementsParAgent result = listeEquipementService.ajouterEquipementParAgent(equipement);

        assertNotNull(result);
        assertEquals("Dell", result.getMarque());
        verify(listeEquipementRepository, times(1)).save(equipement);
    }

    @Test
    void testGetEquipementsParAgent() {
        List<ListeEquipementsParAgent> equipements = Arrays.asList(equipement);
        when(listeEquipementRepository.findAll()).thenReturn(equipements);

        List<ListeEquipementsParAgent> result = listeEquipementService.getEquipementsParAgent();

        assertEquals(1, result.size());
        verify(listeEquipementRepository, times(1)).findAll();
    }

    @Test
    void testGetEquipementParAgentById_Found() {
        when(listeEquipementRepository.findById(1L)).thenReturn(Optional.of(equipement));

        Optional<ListeEquipementsParAgent> result = listeEquipementService.getEquipementParAgentById(1L);

        assertTrue(result.isPresent());
        assertEquals("Dell", result.get().getMarque());
    }

    @Test
    void testGetEquipementParAgentById_NotFound() {
        when(listeEquipementRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ListeEquipementsParAgent> result = listeEquipementService.getEquipementParAgentById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateEquipementParAgent_Found() {
        ListeEquipementsParAgent updatedEquipement = new ListeEquipementsParAgent();
        updatedEquipement.setMarque("HP");
        updatedEquipement.setNature("Imprimante");
        updatedEquipement.setStatutEquipement("UTILISE");

        when(listeEquipementRepository.findById(1L)).thenReturn(Optional.of(equipement));
        when(listeEquipementRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ListeEquipementsParAgent result = listeEquipementService.updateEquipementParAgent(1L, updatedEquipement);

        assertNotNull(result);
        assertEquals("HP", result.getMarque());
        assertEquals("Imprimante", result.getNature());
        assertEquals("UTILISE", result.getStatutEquipement());
    }

    @Test
    void testUpdateEquipementParAgent_NotFound() {
        when(listeEquipementRepository.findById(1L)).thenReturn(Optional.empty());

        ListeEquipementsParAgent result = listeEquipementService.updateEquipementParAgent(1L, equipement);

        assertNull(result);
    }

    @Test
    void testDeleteEquipementParAgent() {
        when(listeEquipementRepository.existsById(1L)).thenReturn(true);

        listeEquipementService.deleteEquipementParAgent(1L);

        verify(listeEquipementRepository, times(1)).deleteById(1L);
    }

    @Test
    void testAffecterAgentAEquipement() {
        when(listeEquipementRepository.findById(1L)).thenReturn(Optional.of(equipement));
        when(listeEquipementRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        listeEquipementService.affecterAgentAEquipement(1L, 123L);

        assertEquals("123", equipement.getUtilisateur());
        verify(listeEquipementRepository, times(1)).save(equipement);
    }

    @Test
    void testGetEquipementsDisponibles() {
        List<ListeEquipementsParAgent> dispo = List.of(equipement);
        when(listeEquipementRepository.findByStatutEquipement("DISPONIBLE")).thenReturn(dispo);

        List<ListeEquipementsParAgent> result = listeEquipementService.getEquipementsDisponibles();

        assertFalse(result.isEmpty());
        verify(listeEquipementRepository, times(1)).findByStatutEquipement("DISPONIBLE");
    }

    @Test
    void testChangerStatutEquipement_Found() {
        when(listeEquipementRepository.findById(1L)).thenReturn(Optional.of(equipement));
        when(listeEquipementRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ListeEquipementsParAgent result = listeEquipementService.changerStatutEquipement(1L, "EN_REPARATION");

        assertEquals("EN_REPARATION", result.getStatutEquipement());
    }

    @Test
    void testChangerStatutEquipement_NotFound() {
        when(listeEquipementRepository.findById(1L)).thenReturn(Optional.empty());

        ListeEquipementsParAgent result = listeEquipementService.changerStatutEquipement(1L, "EN_REPARATION");

        assertNull(result);
    }

    @Test
    void testFindByMarque() {
        List<ListeEquipementsParAgent> liste = List.of(equipement);
        when(listeEquipementRepository.findByMarque("Dell")).thenReturn(liste);

        List<ListeEquipementsParAgent> result = listeEquipementService.findByMarque("Dell");

        assertEquals(1, result.size());
        verify(listeEquipementRepository, times(1)).findByMarque("Dell");
    }
}
