package tn.esprit.equipementservice;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.EquipementReforme;
import tn.esprit.equipementservice.repositories.EquipementReformeRepository;
import tn.esprit.equipementservice.repositories.EquipementRepository;
import tn.esprit.equipementservice.services.EquipementEnReformeService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipementEnReformeServiceTest {

    @InjectMocks
    private EquipementEnReformeService equipementEnReformeService;

    @Mock
    private EquipementReformeRepository equipementReformeRepository;

    @Mock
    private EquipementRepository equipementInformatiqueRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test création EquipementReforme avec EquipementInformatique existant
    @Test
    void testCreateEquipementReforme_Success() {
        EquipementInformatique equipement = new EquipementInformatique();
        equipement.setIdEquipement(1L);

        EquipementReforme equipementReforme = new EquipementReforme();
        equipementReforme.setEquipement(equipement);

        when(equipementInformatiqueRepository.findById(1L)).thenReturn(Optional.of(equipement));
        when(equipementReformeRepository.save(any(EquipementReforme.class))).thenReturn(equipementReforme);

        EquipementReforme result = equipementEnReformeService.createEquipementReforme(equipementReforme);

        assertNotNull(result);
        verify(equipementInformatiqueRepository).findById(1L);
        verify(equipementReformeRepository).save(equipementReforme);
    }

    // Test création EquipementReforme avec EquipementInformatique inexistant -> exception
    @Test
    void testCreateEquipementReforme_EquipementNotFound() {
        EquipementInformatique equipement = new EquipementInformatique();
        equipement.setIdEquipement(999L);

        EquipementReforme equipementReforme = new EquipementReforme();
        equipementReforme.setEquipement(equipement);

        when(equipementInformatiqueRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> equipementEnReformeService.createEquipementReforme(equipementReforme));
        assertEquals("Equipement non trouvé", exception.getMessage());
    }

    // Test création EquipementReforme avec equipement null -> exception
    @Test
    void testCreateEquipementReforme_EquipementNull() {
        EquipementReforme equipementReforme = new EquipementReforme();
        equipementReforme.setEquipement(null);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> equipementEnReformeService.createEquipementReforme(equipementReforme));
        assertEquals("L'équipement associé est null", exception.getMessage());
    }

    // Test récupération par ID
    @Test
    void testGetEquipementReformeById() {
        EquipementReforme equipementReforme = new EquipementReforme();
        equipementReforme.setIdEquipementReforme(1L);

        when(equipementReformeRepository.findById(1L)).thenReturn(Optional.of(equipementReforme));

        Optional<EquipementReforme> result = equipementEnReformeService.getEquipementReformeById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdEquipementReforme());
    }

    // Test récupération liste complète
    @Test
    void testGetAllEquipementsReformes() {
        List<EquipementReforme> list = Arrays.asList(new EquipementReforme(), new EquipementReforme());
        when(equipementReformeRepository.findAll()).thenReturn(list);

        List<EquipementReforme> result = equipementEnReformeService.getAllEquipementsReformes();

        assertEquals(2, result.size());
        verify(equipementReformeRepository).findAll();
    }

    // Test update succès
    @Test
    void testUpdateEquipementReforme_Success() {
        Long id = 1L;

        EquipementInformatique equipement = new EquipementInformatique();
        equipement.setIdEquipement(2L);

        EquipementReforme updated = new EquipementReforme();
        updated.setEquipement(equipement);

        when(equipementReformeRepository.existsById(id)).thenReturn(true);
        when(equipementInformatiqueRepository.findById(2L)).thenReturn(Optional.of(equipement));
        when(equipementReformeRepository.save(any(EquipementReforme.class))).thenAnswer(i -> i.getArguments()[0]);

        EquipementReforme result = equipementEnReformeService.updateEquipementReforme(id, updated);

        assertNotNull(result);
        assertEquals(id, result.getIdEquipementReforme());
        assertEquals(equipement, result.getEquipement());

        verify(equipementReformeRepository).save(updated);
    }

    // Test update échec : EquipementReforme non trouvé -> exception
    @Test
    void testUpdateEquipementReforme_NotFound() {
        when(equipementReformeRepository.existsById(1L)).thenReturn(false);

        EquipementReforme updated = new EquipementReforme();

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> equipementEnReformeService.updateEquipementReforme(1L, updated));
        assertTrue(exception.getMessage().contains("Equipement reformé non trouvé avec l'ID"));
    }

    // Test update échec : EquipementInformatique non trouvé -> exception
    @Test
    void testUpdateEquipementReforme_EquipementInformatiqueNotFound() {
        Long id = 1L;

        EquipementInformatique equipement = new EquipementInformatique();
        equipement.setIdEquipement(99L);

        EquipementReforme updated = new EquipementReforme();
        updated.setEquipement(equipement);

        when(equipementReformeRepository.existsById(id)).thenReturn(true);
        when(equipementInformatiqueRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> equipementEnReformeService.updateEquipementReforme(id, updated));
        assertTrue(exception.getMessage().contains("Équipement informatique non trouvé avec l'ID"));
    }

    // Test suppression succès
    @Test
    void testDeleteEquipementReforme_Success() {
        Long id = 1L;
        when(equipementReformeRepository.existsById(id)).thenReturn(true);

        equipementEnReformeService.deleteEquipementReforme(id);

        verify(equipementReformeRepository).deleteById(id);
    }

    // Test suppression échec -> exception
    @Test
    void testDeleteEquipementReforme_NotFound() {
        Long id = 1L;
        when(equipementReformeRepository.existsById(id)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> equipementEnReformeService.deleteEquipementReforme(id));
        assertTrue(exception.getMessage().contains("Equipement reformé non trouvé avec l'ID"));
    }

    // Test recherche par numeroPatrimoine
    @Test
    void testGetEquipementReformeByNumeroPatrimoine() {
        Long numeroPatrimoine = 123L;

        EquipementReforme equipementReforme = new EquipementReforme();

        when(equipementReformeRepository.findByEquipementIdEquipement(numeroPatrimoine))
                .thenReturn(Optional.of(equipementReforme));

        Optional<EquipementReforme> result = equipementEnReformeService.getEquipementReformeByNumeroPatrimoine(numeroPatrimoine);

        assertTrue(result.isPresent());
        verify(equipementReformeRepository).findByEquipementIdEquipement(numeroPatrimoine);
    }
}

