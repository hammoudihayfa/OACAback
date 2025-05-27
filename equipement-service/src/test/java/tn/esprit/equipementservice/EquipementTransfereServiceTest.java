package tn.esprit.equipementservice;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.ZoneId;
import java.util.Date;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.EquipementTransfere;
import tn.esprit.equipementservice.repositories.EquipementRepository;
import tn.esprit.equipementservice.repositories.EquipementTransfereRepository;
import tn.esprit.equipementservice.services.EquipementTransfereService;

class EquipementTransfereServiceTest {

    @Mock
    private EquipementTransfereRepository equipementTransfereRepository;

    @Mock
    private EquipementRepository equipementRepository;

    @InjectMocks
    private EquipementTransfereService equipementTransfereService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEquipementTransfere() {
        EquipementTransfere transfert = new EquipementTransfere();
        transfert.setIdEquipementTransfere(1L);

        when(equipementTransfereRepository.save(transfert)).thenReturn(transfert);

        EquipementTransfere result = equipementTransfereService.addEquipementTransfere(transfert);

        assertNotNull(result);
        assertEquals(1L, result.getIdEquipementTransfere());
        verify(equipementTransfereRepository, times(1)).save(transfert);
    }

    @Test
    void testGetEquipementTransfereById_Found() {
        EquipementTransfere transfert = new EquipementTransfere();
        transfert.setIdEquipementTransfere(1L);

        when(equipementTransfereRepository.findById(1L)).thenReturn(Optional.of(transfert));

        Optional<EquipementTransfere> result = equipementTransfereService.getEquipementTransfereById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdEquipementTransfere());
    }

    @Test
    void testGetEquipementTransfereById_NotFound() {
        when(equipementTransfereRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<EquipementTransfere> result = equipementTransfereService.getEquipementTransfereById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testGetAllEquipementsTransferes() {
        EquipementTransfere t1 = new EquipementTransfere();
        EquipementTransfere t2 = new EquipementTransfere();

        when(equipementTransfereRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<EquipementTransfere> result = equipementTransfereService.getAllEquipementsTransferes();

        assertEquals(2, result.size());
        verify(equipementTransfereRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEquipementTransfere_Success() {
        EquipementTransfere existing = new EquipementTransfere();
        existing.setIdEquipementTransfere(1L);
        existing.setAncienProprietaire("Ancien");

        EquipementInformatique equipement = new EquipementInformatique();
        equipement.setIdEquipement(10L);

        EquipementTransfere updated = new EquipementTransfere();
        updated.setAncienProprietaire("Nouveau");
        updated.setMatricule("M123");

        // Conversion LocalDate -> Date
        LocalDate localDate = LocalDate.now();
        Date dateTransfert = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        updated.setDateTransfert(dateTransfert);

        updated.setCommentaires("Commentaire");
        updated.setEquipement(equipement);

        when(equipementTransfereRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(equipementRepository.findById(10L)).thenReturn(Optional.of(equipement));
        when(equipementTransfereRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        EquipementTransfere result = equipementTransfereService.updateEquipementTransfere(1L, updated);

        assertEquals("Nouveau", result.getAncienProprietaire());
        assertEquals("M123", result.getMatricule());
        assertEquals("Commentaire", result.getCommentaires());
        assertEquals(equipement, result.getEquipement());

        verify(equipementTransfereRepository, times(1)).save(existing);
    }

    @Test
    void testUpdateEquipementTransfere_TransfertNotFound() {
        EquipementTransfere updated = new EquipementTransfere();

        when(equipementTransfereRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            equipementTransfereService.updateEquipementTransfere(1L, updated);
        });

        assertEquals("Transfert avec l'ID 1 non trouvé.", ex.getMessage());
        verify(equipementTransfereRepository, never()).save(any());
    }

    @Test
    void testUpdateEquipementTransfere_EquipementNotFound() {
        EquipementTransfere existing = new EquipementTransfere();
        existing.setIdEquipementTransfere(1L);

        EquipementInformatique equipement = new EquipementInformatique();
        equipement.setIdEquipement(10L);

        EquipementTransfere updated = new EquipementTransfere();
        updated.setEquipement(equipement);

        when(equipementTransfereRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(equipementRepository.findById(10L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            equipementTransfereService.updateEquipementTransfere(1L, updated);
        });

        assertEquals("Équipement avec l'ID 10 non trouvé.", ex.getMessage());
        verify(equipementTransfereRepository, never()).save(any());
    }

    @Test
    void testDeleteEquipementTransfere_Success() {
        when(equipementTransfereRepository.existsById(1L)).thenReturn(true);
        doNothing().when(equipementTransfereRepository).deleteById(1L);

        assertDoesNotThrow(() -> equipementTransfereService.deleteEquipementTransfere(1L));

        verify(equipementTransfereRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEquipementTransfere_NotFound() {
        when(equipementTransfereRepository.existsById(1L)).thenReturn(false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            equipementTransfereService.deleteEquipementTransfere(1L);
        });

        assertEquals("Transfert avec l'ID 1 non trouvé.", ex.getMessage());
        verify(equipementTransfereRepository, never()).deleteById(any());
    }

    @Test
    void testGetEquipementsTransferesByEquipementId() {
        EquipementTransfere t1 = new EquipementTransfere();
        EquipementTransfere t2 = new EquipementTransfere();

        when(equipementTransfereRepository.findByEquipement_IdEquipement(5L)).thenReturn(Arrays.asList(t1, t2));

        List<EquipementTransfere> result = equipementTransfereService.getEquipementsTransferesByEquipementId(5L);

        assertEquals(2, result.size());
        verify(equipementTransfereRepository, times(1)).findByEquipement_IdEquipement(5L);
    }
}
