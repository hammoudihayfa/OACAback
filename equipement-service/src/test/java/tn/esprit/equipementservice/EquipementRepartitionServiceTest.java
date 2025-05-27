package tn.esprit.equipementservice;


import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.EquipementRepartition;
import tn.esprit.equipementservice.repositories.EquipementRepartitionRepository;
import tn.esprit.equipementservice.repositories.EquipementRepository;
import tn.esprit.equipementservice.services.EquipementRepartitionService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Date;
import java.time.ZoneId;


class EquipementRepartitionServiceTest {

    @InjectMocks
    private EquipementRepartitionService equipementRepartitionService;

    @Mock
    private EquipementRepartitionRepository equipementRepartitionRepository;

    @Mock
    private EquipementRepository equipementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test createEquipementRepartition avec equipement existant
    @Test
    void testCreateEquipementRepartition_WithExistingEquipement() {
        EquipementInformatique equipement = new EquipementInformatique();
        equipement.setIdEquipement(1L);

        EquipementRepartition repartition = new EquipementRepartition();
        repartition.setEquipement(equipement);

        when(equipementRepository.findById(1L)).thenReturn(Optional.of(equipement));
        when(equipementRepartitionRepository.save(any(EquipementRepartition.class))).thenReturn(repartition);

        EquipementRepartition result = equipementRepartitionService.createEquipementRepartition(repartition);

        assertNotNull(result);
        verify(equipementRepository).findById(1L);
        verify(equipementRepartitionRepository).save(repartition);
    }

    // Test createEquipementRepartition avec equipement inexistant
    @Test
    void testCreateEquipementRepartition_EquipementNotFound() {
        EquipementInformatique equipement = new EquipementInformatique();
        equipement.setIdEquipement(999L);

        EquipementRepartition repartition = new EquipementRepartition();
        repartition.setEquipement(equipement);

        when(equipementRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                equipementRepartitionService.createEquipementRepartition(repartition));
    }

    //  Test getAllEquipementsRepartition
    @Test
    void testGetAllEquipementsRepartition() {
        List<EquipementRepartition> list = Arrays.asList(new EquipementRepartition(), new EquipementRepartition());
        when(equipementRepartitionRepository.findAll()).thenReturn(list);

        List<EquipementRepartition> result = equipementRepartitionService.getAllEquipementsRepartition();

        assertEquals(2, result.size());
        verify(equipementRepartitionRepository).findAll();
    }

    //  Test getEquipementRepartitionById
    @Test
    void testGetEquipementRepartitionById() {
        EquipementRepartition repartition = new EquipementRepartition();
        repartition.setIdEquipementRepartition(1L);

        when(equipementRepartitionRepository.findById(1L)).thenReturn(Optional.of(repartition));

        Optional<EquipementRepartition> result = equipementRepartitionService.getEquipementRepartitionById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdEquipementRepartition());
    }

    // Test updateEquipementRepartition - success
    @Test
    void testUpdateEquipementRepartition_Success() {
        Long id = 1L;

        EquipementRepartition existing = new EquipementRepartition();
        existing.setIdEquipementRepartition(id);
        existing.setLocalisation("A");

        EquipementRepartition updated = new EquipementRepartition();
        updated.setLocalisation("B");
        updated.setUniteResponsable("IT");

        updated.setDateDebut(Date.valueOf(LocalDate.of(2024, 1, 1)));
        updated.setDateFin(Date.valueOf(LocalDate.of(2024, 12, 31)));

        when(equipementRepartitionRepository.findById(id)).thenReturn(Optional.of(existing));
        when(equipementRepartitionRepository.save(any(EquipementRepartition.class))).thenReturn(updated);

        EquipementRepartition result = equipementRepartitionService.updateEquipementRepartition(id, updated);

        assertNotNull(result);
        assertEquals("B", result.getLocalisation());
        assertEquals("IT", result.getUniteResponsable());
        verify(equipementRepartitionRepository).save(existing);
    }


    //  Test updateEquipementRepartition - not found
    @Test
    void testUpdateEquipementRepartition_NotFound() {
        when(equipementRepartitionRepository.findById(1L)).thenReturn(Optional.empty());

        EquipementRepartition updated = new EquipementRepartition();
        EquipementRepartition result = equipementRepartitionService.updateEquipementRepartition(1L, updated);

        assertNull(result);
    }

    // Test deleteEquipementRepartition - existant
    @Test
    void testDeleteEquipementRepartition_Exists() {
        when(equipementRepartitionRepository.existsById(1L)).thenReturn(true);

        equipementRepartitionService.deleteEquipementRepartition(1L);

        verify(equipementRepartitionRepository).deleteById(1L);
    }

    //  Test deleteEquipementRepartition - inexistant
    @Test
    void testDeleteEquipementRepartition_NotExists() {
        when(equipementRepartitionRepository.existsById(1L)).thenReturn(false);

        equipementRepartitionService.deleteEquipementRepartition(1L);

        verify(equipementRepartitionRepository, never()).deleteById(anyLong());
    }
}

