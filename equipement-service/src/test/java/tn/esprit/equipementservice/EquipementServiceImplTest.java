package tn.esprit.equipementservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.repositories.EquipementRepository;
import tn.esprit.equipementservice.services.EquipementService;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

import static org.mockito.Mockito.doNothing;

public class EquipementServiceImplTest {

    @Mock
    private EquipementRepository equipementRepository;

    @InjectMocks
    private EquipementService equipementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEquipementById_NotFound() {
        Long id = 2L;

        when(equipementRepository.findById(id)).thenReturn(Optional.empty());

        Optional<EquipementInformatique> result = equipementService.getEquipementById(id);

        assertTrue(result.isEmpty());
        verify(equipementRepository, times(1)).findById(id);
    }

    @Test
    void testAjouterEquipement() {
        EquipementInformatique equipement = new EquipementInformatique();
        when(equipementRepository.save(equipement)).thenReturn(equipement);

        EquipementInformatique saved = equipementService.ajouterEquipement(equipement);

        assertEquals(equipement, saved);
        verify(equipementRepository, times(1)).save(equipement);
    }

    @Test
    void testListeEquipements() {
        List<EquipementInformatique> equipements = Arrays.asList(new EquipementInformatique(), new EquipementInformatique());
        when(equipementRepository.findAll()).thenReturn(equipements);

        List<EquipementInformatique> result = equipementService.listeEquipements();

        assertEquals(2, result.size());
        verify(equipementRepository, times(1)).findAll();
    }

    @Test
    void testGetEquipementById_Found() {
        Long id = 1L;
        EquipementInformatique equipement = new EquipementInformatique();
        equipement.setIdEquipement(id);

        when(equipementRepository.findById(id)).thenReturn(Optional.of(equipement));

        Optional<EquipementInformatique> result = equipementService.getEquipementById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getIdEquipement());
    }



    @Test
    void testDeleteEquipement_NotFound() {
        Long id = 1L;
        when(equipementRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> equipementService.deleteEquipement(id));
        verify(equipementRepository, never()).deleteById(anyLong());
    }



}
