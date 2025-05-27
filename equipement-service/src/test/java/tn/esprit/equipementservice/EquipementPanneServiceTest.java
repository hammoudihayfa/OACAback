package tn.esprit.equipementservice;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.repositories.EquipementEnPanneRepository;
import tn.esprit.equipementservice.repositories.EquipementRepository;
import tn.esprit.equipementservice.services.EquipementPanneService;

class EquipementPanneServiceTest {

    @Mock
    private EquipementEnPanneRepository equipementEnPanneRepository;

    @Mock
    private EquipementRepository equipementRepository;

    @InjectMocks
    private EquipementPanneService equipementPanneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAjouterEquipementEnPanne() {
        EquipementEnPanne equipement = new EquipementEnPanne();
        equipement.setIdEquipementEnPanne(1L);

        when(equipementEnPanneRepository.save(equipement)).thenReturn(equipement);

        EquipementEnPanne result = equipementPanneService.ajouterEquipementEnPanne(equipement);

        assertNotNull(result);
        assertEquals(1L, result.getIdEquipementEnPanne());
        verify(equipementEnPanneRepository, times(1)).save(equipement);
    }

    @Test
    void testListeEquipementsEnPanne() {
        EquipementEnPanne e1 = new EquipementEnPanne();
        EquipementEnPanne e2 = new EquipementEnPanne();

        when(equipementEnPanneRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<EquipementEnPanne> result = equipementPanneService.listeEquipementsEnPanne();

        assertEquals(2, result.size());
        verify(equipementEnPanneRepository, times(1)).findAll();
    }

    @Test
    void testGetEquipementEnPanneById_Found() {
        EquipementEnPanne equipement = new EquipementEnPanne();
        equipement.setIdEquipementEnPanne(1L);

        when(equipementEnPanneRepository.findById(1L)).thenReturn(Optional.of(equipement));

        Optional<EquipementEnPanne> result = equipementPanneService.getEquipementEnPanneById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdEquipementEnPanne());
    }

    @Test
    void testGetEquipementEnPanneById_NotFound() {
        when(equipementEnPanneRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<EquipementEnPanne> result = equipementPanneService.getEquipementEnPanneById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateEquipementEnPanne_Exists() {
        EquipementEnPanne equipement = new EquipementEnPanne();

        when(equipementEnPanneRepository.existsById(1L)).thenReturn(true);
        when(equipementEnPanneRepository.save(any(EquipementEnPanne.class))).thenAnswer(i -> i.getArgument(0));

        EquipementEnPanne updated = equipementPanneService.updateEquipementEnPanne(1L, equipement);

        assertNotNull(updated);
        assertEquals(1L, updated.getIdEquipementEnPanne());
        verify(equipementEnPanneRepository, times(1)).save(equipement);
    }

    @Test
    void testUpdateEquipementEnPanne_NotExists() {
        EquipementEnPanne equipement = new EquipementEnPanne();

        when(equipementEnPanneRepository.existsById(1L)).thenReturn(false);

        EquipementEnPanne updated = equipementPanneService.updateEquipementEnPanne(1L, equipement);

        assertNull(updated);
        verify(equipementEnPanneRepository, never()).save(any());
    }

    @Test
    void testDeleteEquipementEnPanne() {
        doNothing().when(equipementEnPanneRepository).deleteById(1L);

        equipementPanneService.deleteEquipementEnPanne(1L);

        verify(equipementEnPanneRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetGlobalFailureRate() {
        when(equipementRepository.count()).thenReturn(10L);
        when(equipementEnPanneRepository.count()).thenReturn(2L);

        double rate = equipementPanneService.getGlobalFailureRate();

        assertEquals(0.2, rate, 0.0001);
    }

    @Test
    void testGetGlobalFailureRate_NoEquipement() {
        when(equipementRepository.count()).thenReturn(0L);

        double rate = equipementPanneService.getGlobalFailureRate();

        assertEquals(0.0, rate, 0.0001);
    }
}

