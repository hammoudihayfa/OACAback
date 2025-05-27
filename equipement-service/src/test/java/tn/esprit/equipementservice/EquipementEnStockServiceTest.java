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

import tn.esprit.equipementservice.entities.EquipementEnStock;
import tn.esprit.equipementservice.repositories.EquipementEnStockRepository;
import tn.esprit.equipementservice.services.EquipementEnStockService;

class EquipementEnStockServiceTest {

    @Mock
    private EquipementEnStockRepository equipementEnStockRepository;

    @InjectMocks
    private EquipementEnStockService equipementEnStockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEquipement() {
        EquipementEnStock equipement = new EquipementEnStock();
        equipement.setTypeEquipement("Ordinateur");
        equipement.setQuantite(10L);
        equipement.setAlerteStock(2L);

        when(equipementEnStockRepository.save(equipement)).thenReturn(equipement);

        EquipementEnStock result = equipementEnStockService.createEquipement(equipement);

        assertNotNull(result);
        assertEquals("Ordinateur", result.getTypeEquipement());
        verify(equipementEnStockRepository, times(1)).save(equipement);
    }

    @Test
    void testGetEquipementById_Found() {
        EquipementEnStock equipement = new EquipementEnStock();
        equipement.setIdStock(1L);
        equipement.setTypeEquipement("Imprimante");

        when(equipementEnStockRepository.findById(1L)).thenReturn(Optional.of(equipement));

        Optional<EquipementEnStock> result = equipementEnStockService.getEquipementById(1L);

        assertTrue(result.isPresent());
        assertEquals("Imprimante", result.get().getTypeEquipement());
    }

    @Test
    void testGetEquipementById_NotFound() {
        when(equipementEnStockRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<EquipementEnStock> result = equipementEnStockService.getEquipementById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testGetAllEquipements() {
        EquipementEnStock e1 = new EquipementEnStock();
        e1.setIdStock(1L);
        EquipementEnStock e2 = new EquipementEnStock();
        e2.setIdStock(2L);

        when(equipementEnStockRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<EquipementEnStock> result = equipementEnStockService.getAllEquipements();

        assertEquals(2, result.size());
        verify(equipementEnStockRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEquipement_Success() {
        EquipementEnStock existing = new EquipementEnStock();
        existing.setIdStock(1L);
        existing.setTypeEquipement("Ancien Type");
        existing.setQuantite(5L);

        EquipementEnStock updated = new EquipementEnStock();
        updated.setTypeEquipement("Nouveau Type");
        updated.setQuantite(15L);
        updated.setAlerteStock(3L);

        when(equipementEnStockRepository.existsById(1L)).thenReturn(true);
        when(equipementEnStockRepository.save(any(EquipementEnStock.class))).thenAnswer(i -> i.getArgument(0));

        EquipementEnStock result = equipementEnStockService.updateEquipement(1L, updated);

        assertEquals(1L, result.getIdStock());
        assertEquals("Nouveau Type", result.getTypeEquipement());
        assertEquals(15L, result.getQuantite());
        assertEquals(3L, result.getAlerteStock());

        verify(equipementEnStockRepository, times(1)).existsById(1L);
        verify(equipementEnStockRepository, times(1)).save(updated);
    }

    @Test
    void testUpdateEquipement_NotFound() {
        EquipementEnStock updated = new EquipementEnStock();

        when(equipementEnStockRepository.existsById(1L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            equipementEnStockService.updateEquipement(1L, updated);
        });

        assertEquals("Equipement not found with id: 1", ex.getMessage());
        verify(equipementEnStockRepository, never()).save(any());
    }

    @Test
    void testDeleteEquipement_Success() {
        when(equipementEnStockRepository.existsById(1L)).thenReturn(true);
        doNothing().when(equipementEnStockRepository).deleteById(1L);

        assertDoesNotThrow(() -> equipementEnStockService.deleteEquipement(1L));

        verify(equipementEnStockRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEquipement_NotFound() {
        when(equipementEnStockRepository.existsById(1L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            equipementEnStockService.deleteEquipement(1L);
        });

        assertEquals("Equipement not found with id: 1", ex.getMessage());
        verify(equipementEnStockRepository, never()).deleteById(any());
    }
}
