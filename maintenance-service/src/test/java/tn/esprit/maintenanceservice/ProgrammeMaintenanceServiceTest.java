package tn.esprit.maintenanceservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.maintenanceservice.entities.FrequencyType;
import tn.esprit.maintenanceservice.entities.ProgrammeMaintenance;
import tn.esprit.maintenanceservice.repositories.ProgrammeMaintenanceRepository;
import tn.esprit.maintenanceservice.services.ProgrammeMaintenanceService;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeMaintenanceServiceTest {

    @Mock
    private ProgrammeMaintenanceRepository programmeMaintenanceRepository;

    @InjectMocks
    private ProgrammeMaintenanceService programmeMaintenanceService;

    private ProgrammeMaintenance programme;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        programme = new ProgrammeMaintenance();
        programme.setIdProgrammeMaintenance(1L);
        programme.setPlanAction("Plan A");
        programme.setProcedureMaintenance("Procédure A");
        programme.setActionCorrective("Action A");
        programme.setObservation("Observation A");
        programme.setResponsable("Jean Dupont");
        programme.setEtudeSecurite("Etude 1");
        programme.setStartDate(LocalDate.of(2025, 5, 26));
        programme.setFrequencyType(FrequencyType.MONTHLY);
        programme.setEstimatedDurationDays(10);
    }

    @Test
    void testAjouterProgrammeMaintenance_setsNextDueDate() {
        when(programmeMaintenanceRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ProgrammeMaintenance saved = programmeMaintenanceService.ajouterProgrammeMaintenance(programme);

        assertNotNull(saved.getNextDueDate());
        assertEquals(programme.getStartDate().plusMonths(1), saved.getNextDueDate());
        verify(programmeMaintenanceRepository, times(1)).save(programme);
    }

    @Test
    void testGetAllProgrammesMaintenance() {
        List<ProgrammeMaintenance> list = List.of(programme);
        when(programmeMaintenanceRepository.findAll()).thenReturn(list);

        List<ProgrammeMaintenance> result = programmeMaintenanceService.getAllProgrammesMaintenance();

        assertEquals(1, result.size());
        verify(programmeMaintenanceRepository, times(1)).findAll();
    }

    @Test
    void testGetProgrammeMaintenanceById_found() {
        when(programmeMaintenanceRepository.findById(1L)).thenReturn(Optional.of(programme));

        Optional<ProgrammeMaintenance> result = programmeMaintenanceService.getProgrammeMaintenanceById(1L);

        assertTrue(result.isPresent());
        assertEquals("Plan A", result.get().getPlanAction());
    }

    @Test
    void testGetProgrammeMaintenanceById_notFound() {
        when(programmeMaintenanceRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ProgrammeMaintenance> result = programmeMaintenanceService.getProgrammeMaintenanceById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateProgrammeMaintenance_existing() {
        ProgrammeMaintenance updatedDetails = new ProgrammeMaintenance();
        updatedDetails.setPlanAction("Updated Plan");
        updatedDetails.setProcedureMaintenance("Updated Proc");
        updatedDetails.setActionCorrective("Updated Action");
        updatedDetails.setObservation("Updated Obs");
        updatedDetails.setResponsable("Marie Curie");
        updatedDetails.setEtudeSecurite("Updated Etude");
        updatedDetails.setStartDate(LocalDate.of(2025, 6, 1));
        updatedDetails.setFrequencyType(FrequencyType.YEARLY);
        updatedDetails.setEstimatedDurationDays(15);

        when(programmeMaintenanceRepository.findById(1L)).thenReturn(Optional.of(programme));
        when(programmeMaintenanceRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ProgrammeMaintenance result = programmeMaintenanceService.updateProgrammeMaintenance(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("Updated Plan", result.getPlanAction());
        assertEquals(LocalDate.of(2025, 6, 1).plusYears(1), result.getNextDueDate());
        verify(programmeMaintenanceRepository, times(1)).save(programme);
    }

    @Test
    void testUpdateProgrammeMaintenance_notFound() {
        when(programmeMaintenanceRepository.findById(1L)).thenReturn(Optional.empty());

        ProgrammeMaintenance result = programmeMaintenanceService.updateProgrammeMaintenance(1L, programme);

        assertNull(result);
        verify(programmeMaintenanceRepository, never()).save(any());
    }

    @Test
    void testDeleteProgrammeMaintenance() {
        doNothing().when(programmeMaintenanceRepository).deleteById(1L);

        programmeMaintenanceService.deleteProgrammeMaintenance(1L);

        verify(programmeMaintenanceRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetProgrammesMaintenanceByResponsable() {
        List<ProgrammeMaintenance> list = List.of(programme);
        when(programmeMaintenanceRepository.findByResponsableContainingIgnoreCase("Jean")).thenReturn(list);

        List<ProgrammeMaintenance> result = programmeMaintenanceService.getProgrammesMaintenanceByResponsable("Jean");

        assertEquals(1, result.size());
        verify(programmeMaintenanceRepository, times(1)).findByResponsableContainingIgnoreCase("Jean");
    }


}
