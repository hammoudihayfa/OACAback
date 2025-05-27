package tn.esprit.logicielservice;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.logicielservice.entities.ListeLogiciel;
import tn.esprit.logicielservice.repositories.ListeLogicielRepository;
import tn.esprit.logicielservice.services.ListeLogicielService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListeLogicielServiceTest {

    @Mock
    private ListeLogicielRepository listeLogicielRepository;

    @InjectMocks
    private ListeLogicielService listeLogicielService;

    private ListeLogiciel logiciel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logiciel = new ListeLogiciel();
        logiciel.setIdLogiciel(1L);
        logiciel.setNomLogiciel("Photoshop");
        logiciel.setVersion("2023");
        logiciel.setNumeroLicence("ABC123");
        logiciel.setContrat("Annuel");
        logiciel.setDateLimite(new Date());
    }

    @Test
    void testCreateLogiciel() {
        when(listeLogicielRepository.save(logiciel)).thenReturn(logiciel);

        ListeLogiciel result = listeLogicielService.createLogiciel(logiciel);

        assertNotNull(result);
        assertEquals("Photoshop", result.getNomLogiciel());
        verify(listeLogicielRepository, times(1)).save(logiciel);
    }

    @Test
    void testGetAllLogiciels() {
        List<ListeLogiciel> logiciels = Arrays.asList(logiciel);
        when(listeLogicielRepository.findAll()).thenReturn(logiciels);

        List<ListeLogiciel> result = listeLogicielService.getAllLogiciels();

        assertEquals(1, result.size());
        verify(listeLogicielRepository, times(1)).findAll();
    }

    @Test
    void testGetLogicielById_Found() {
        when(listeLogicielRepository.findById(1L)).thenReturn(Optional.of(logiciel));

        Optional<ListeLogiciel> result = listeLogicielService.getLogicielById(1L);

        assertTrue(result.isPresent());
        assertEquals("Photoshop", result.get().getNomLogiciel());
    }

    @Test
    void testGetLogicielById_NotFound() {
        when(listeLogicielRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ListeLogiciel> result = listeLogicielService.getLogicielById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateLogiciel_Exists() {
        ListeLogiciel updatedLogiciel = new ListeLogiciel();
        updatedLogiciel.setNomLogiciel("Illustrator");
        updatedLogiciel.setVersion("2024");

        when(listeLogicielRepository.existsById(1L)).thenReturn(true);
        when(listeLogicielRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ListeLogiciel result = listeLogicielService.updateLogiciel(1L, updatedLogiciel);

        assertNotNull(result);
        assertEquals("Illustrator", result.getNomLogiciel());
        assertEquals(1L, result.getIdLogiciel());
    }

    @Test
    void testUpdateLogiciel_NotExists() {
        when(listeLogicielRepository.existsById(1L)).thenReturn(false);

        ListeLogiciel result = listeLogicielService.updateLogiciel(1L, logiciel);

        assertNull(result);
        verify(listeLogicielRepository, never()).save(any());
    }

    @Test
    void testDeleteLogiciel() {
        doNothing().when(listeLogicielRepository).deleteById(1L);

        listeLogicielService.deleteLogiciel(1L);

        verify(listeLogicielRepository, times(1)).deleteById(1L);
    }

    @Test
    void testSearchByNomLogiciel() {
        List<ListeLogiciel> logiciels = List.of(logiciel);
        when(listeLogicielRepository.findByNomLogicielContainingIgnoreCase("photo")).thenReturn(logiciels);

        List<ListeLogiciel> result = listeLogicielService.searchByNomLogiciel("photo");

        assertEquals(1, result.size());
        verify(listeLogicielRepository, times(1)).findByNomLogicielContainingIgnoreCase("photo");
    }

    @Test
    void testSearchByVersion() {
        List<ListeLogiciel> logiciels = List.of(logiciel);
        when(listeLogicielRepository.findByVersionContainingIgnoreCase("2023")).thenReturn(logiciels);

        List<ListeLogiciel> result = listeLogicielService.searchByVersion("2023");

        assertEquals(1, result.size());
        verify(listeLogicielRepository, times(1)).findByVersionContainingIgnoreCase("2023");
    }
}
