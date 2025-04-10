package tn.esprit.equipementservice.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.DTO.ListeLogicielDTO;
import tn.esprit.equipementservice.DTO.LogicielClient;
import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.HistoriqueMouvement;
import tn.esprit.equipementservice.repositories.EquipementEnPanneRepository;
import tn.esprit.equipementservice.repositories.EquipementRepository;
import tn.esprit.equipementservice.repositories.HistoriqueMouvementRepository;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipementService implements IEquipement {
    private final EquipementRepository equipementRepository;
    private final LogicielClient logicielClient;

    @Autowired
    public EquipementService(EquipementRepository equipementRepository, LogicielClient logicielClient) {
        this.equipementRepository = equipementRepository;
        this.logicielClient = logicielClient;
    }
    @Autowired
    private EquipementEnPanneRepository equipementEnPanneRepository;

    @Autowired
    private HistoriqueMouvementRepository historiqueMouvementRepository;

    public List<ListeLogicielDTO> getLogicielsParEquipement(Long equipementId) {
        return logicielClient.getLogicielsByEquipement(equipementId);
    }

    public List<EquipementInformatique> getEquipementsByMatricule(int matricule) {
        return equipementRepository.findAll().stream()
                .filter(e -> e.getMatricule() == matricule)
                .collect(Collectors.toList());
    }

    @Override
    public EquipementInformatique ajouterEquipement(EquipementInformatique equipement) {
        return equipementRepository.save(equipement);
    }

    @Override
    public List<EquipementInformatique> listeEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Optional<EquipementInformatique> getEquipementById(Long numeroPatrimoine) {
        return equipementRepository.findById(numeroPatrimoine);
    }

    @Override
    public EquipementInformatique updateEquipement(Long numeroPatrimoine, EquipementInformatique equipement) {
        return equipementRepository.findById(numeroPatrimoine)
                .map(equipementToUpdate -> {
                    equipementToUpdate.setMatricule(equipement.getMatricule());
                    equipementToUpdate.setTypeEquipement(equipement.getTypeEquipement());
                    equipementToUpdate.setMarqueEquipement(equipement.getMarqueEquipement());
                    equipementToUpdate.setModeleEquipement(equipement.getModeleEquipement());
                    equipementToUpdate.setNumeroSerie(equipement.getNumeroSerie());
                    equipementToUpdate.setDateMiseEnService(equipement.getDateMiseEnService());
                    return equipementRepository.save(equipementToUpdate);
                })
                .orElse(null);
    }

    @Override
    public void deleteEquipement(Long numeroPatrimoine) {
        if (equipementRepository.existsById(numeroPatrimoine)) {
            equipementRepository.deleteById(numeroPatrimoine);
        }
    }

    @Override
    public BufferedImage genererQRCode(int matricule) throws Exception {
        // Récupérer l'équipement en fonction du matricule
        EquipementInformatique equipement = equipementRepository.findByMatricule(matricule);
        if (equipement == null) {
            throw new Exception("Équipement non trouvé");
        }

        // Construire la chaîne des informations de l'équipement à afficher dans le QR Code
        String equipementDetails = "Matricule: " + equipement.getMatricule() + "\n" +
                "Nom: " + equipement.getNom() + "\n" +
                "Type: " + equipement.getTypeEquipement() + "\n" +
                "Marque: " + equipement.getMarqueEquipement() + "\n" +
                "Modèle: " + equipement.getModeleEquipement() + "\n" +
                "Numéro Série: " + equipement.getNumeroSerie() + "\n" +
                "Date Mise En Service: " + equipement.getDateMiseEnService();

        // Créer un QR Code avec les détails de l'équipement
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix matrix = writer.encode(equipementDetails, BarcodeFormat.QR_CODE, 200, 200, hints);

        BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0x000000 : 0xFFFFFF);
            }
        }

        return image;
    }

    @Override
    public EquipementEnPanne declarePanne(Long equipementId, String descriptionPanne) {
        EquipementInformatique equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new RuntimeException("Equipement non trouvé"));

        EquipementEnPanne panne = new EquipementEnPanne();
        panne.setEquipement(equipement);
        panne.setDescription(descriptionPanne);

        // Convertir LocalDate en Date
        LocalDate localDate = LocalDate.now();
        Date datePanne = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        panne.setDatePanne(datePanne);

        return equipementEnPanneRepository.save(panne);  }

    @Override
    public List<EquipementEnPanne> getPannesByEquipement(Long equipementId) {
        return equipementEnPanneRepository.findByEquipementNumeroPatrimoine(equipementId);
    }

    @Override
    public HistoriqueMouvement ajouterMouvement(Long equipementId, String typeMouvement) {
        EquipementInformatique equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new RuntimeException("Equipement non trouvé"));

        HistoriqueMouvement mouvement = new HistoriqueMouvement();
        mouvement.setEquipement(equipement);
        mouvement.setTypeMouvement(typeMouvement);
        mouvement.setDateMouvement(LocalDate.now());

        return historiqueMouvementRepository.save(mouvement);
    }

    @Override
    public List<HistoriqueMouvement> getHistoriqueMouvements(Long equipementId) {
        return historiqueMouvementRepository.findByEquipementNumeroPatrimoine(equipementId);
    }

}
