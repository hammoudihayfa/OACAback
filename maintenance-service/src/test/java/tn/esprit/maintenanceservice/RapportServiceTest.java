package tn.esprit.maintenanceservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.maintenanceservice.entities.ProgrammeMaintenance;
import tn.esprit.maintenanceservice.entities.Rapport;
import tn.esprit.maintenanceservice.repositories.RapportRepository;
import tn.esprit.maintenanceservice.services.RapportService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RapportServiceTest {

    @Mock
    private RapportRepository rapportRepository;

    @InjectMocks
    private RapportService rapportService;

    private Rapport rapport;
    private ProgrammeMaintenance programme;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        programme = new ProgrammeMaintenance();
        programme.setIdProgrammeMaintenance(1L);

        rapport = new Rapport();
        rapport.setIdRapport(1L);
        rapport.setTitre("Rapport Test");
        rapport.setContenu("Contenu du rapport");
        rapport.setProgramme(programme);
        // dateCreation sera défini automatiquement à l'ajout
    }

    @Test
    void testGetAllRapports() {
        List<Rapport> rapports = List.of(rapport);
        when(rapportRepository.findAll()).thenReturn(rapports);

        List<Rapport> result = rapportService.getAllRapports();

        assertEquals(1, result.size());
        verify(rapportRepository, times(1)).findAll();
    }

    @Test
    void testGetRapportById_found() {
        when(rapportRepository.findById(1L)).thenReturn(Optional.of(rapport));

        Rapport result = rapportService.getRapportById(1L);

        assertNotNull(result);
        assertEquals("Rapport Test", result.getTitre());
    }

    @Test
    void testGetRapportById_notFound() {
        when(rapportRepository.findById(1L)).thenReturn(Optional.empty());

        Rapport result = rapportService.getRapportById(1L);

        assertNull(result);
    }

    @Test
    void testAddRapport_setsDateCreationAndSave() {
        when(rapportRepository.save(any(Rapport.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Rapport saved = rapportService.addRapport(rapport);

        assertNotNull(saved.getDateCreation());
        assertEquals(LocalDate.now(), saved.getDateCreation());
        verify(rapportRepository, times(1)).save(rapport);
    }

    @Test
    void testDeleteRapport() {
        doNothing().when(rapportRepository).deleteById(1L);

        rapportService.deleteRapport(1L);

        verify(rapportRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetRapportsByProgramme() {
        List<Rapport> rapports = List.of(rapport);
        when(rapportRepository.findByProgramme_IdProgrammeMaintenance(1L)).thenReturn(rapports);

        List<Rapport> result = rapportService.getRapportsByProgramme(1L);

        assertEquals(1, result.size());
        verify(rapportRepository, times(1)).findByProgramme_IdProgrammeMaintenance(1L);
    }

    @Test
    void testGetRapportsByDateCreationBetween() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);
        List<Rapport> rapports = List.of(rapport);

        when(rapportRepository.findByDateCreationBetween(startDate, endDate)).thenReturn(rapports);

        List<Rapport> result = rapportService.getRapportsByDateCreationBetween(startDate, endDate);

        assertEquals(1, result.size());
        verify(rapportRepository, times(1)).findByDateCreationBetween(startDate, endDate);
    }
}

