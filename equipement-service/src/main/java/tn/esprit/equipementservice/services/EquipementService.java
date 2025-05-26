package tn.esprit.equipementservice.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import jakarta.transaction.Transactional;
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
import java.util.*;
import java.util.stream.Collectors;
@Service
public class EquipementService implements IEquipement {
    private final EquipementRepository equipementRepository;
    private final LogicielClient logicielClient;
    private final EquipementEnPanneRepository equipementEnPanneRepository;
    private final HistoriqueMouvementRepository mouvementRepository;

    @Autowired
    public EquipementService(EquipementRepository equipementRepository, LogicielClient logicielClient,
                             EquipementEnPanneRepository equipementEnPanneRepository,
                             HistoriqueMouvementRepository mouvementRepository) {
        this.equipementRepository = equipementRepository;
        this.logicielClient = logicielClient;
        this.equipementEnPanneRepository = equipementEnPanneRepository;
        this.mouvementRepository = mouvementRepository;
    }

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
        Optional<EquipementInformatique> optionalEquipement = equipementRepository.findById(numeroPatrimoine);
        if (optionalEquipement.isPresent()) {
            EquipementInformatique existingEquipement = optionalEquipement.get();
            existingEquipement.setMatricule(equipement.getMatricule());
            existingEquipement.setTypeEquipement(equipement.getTypeEquipement());
            existingEquipement.setMarqueEquipement(equipement.getMarqueEquipement());
            existingEquipement.setModeleEquipement(equipement.getModeleEquipement());
            existingEquipement.setLocalisation(equipement.getLocalisation());
            existingEquipement.setNumeroSerie(equipement.getNumeroSerie());
            existingEquipement.setDateMiseEnService(equipement.getDateMiseEnService());
            existingEquipement.setEtat(equipement.getEtat());
            return equipementRepository.save(existingEquipement);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteEquipement(Long numeroPatrimoine) {
        Optional<EquipementInformatique> equipement = equipementRepository.findById(numeroPatrimoine);
        if (equipement.isPresent()) {
            try {
                equipementEnPanneRepository.deleteByEquipement_IdEquipement(numeroPatrimoine);

                mouvementRepository.deleteByEquipement_IdEquipement(numeroPatrimoine);

                equipementRepository.deleteById(numeroPatrimoine);

                System.out.println("Équipement supprimé avec succès : " + numeroPatrimoine);

            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de la suppression de l'équipement avec l'ID " + numeroPatrimoine, e);
            }
        } else {
            throw new NoSuchElementException("L'équipement avec l'ID " + numeroPatrimoine + " n'a pas été trouvé.");
        }
    }

    @Override
    public BufferedImage genererQRCode(String numeroSerie) throws Exception {
        EquipementInformatique equipement = equipementRepository.findByNumeroSerie(numeroSerie);
        if (equipement == null) {
            throw new Exception("Équipement non trouvé avec le numéro de série: " + numeroSerie);
        }

        String equipementDetails = "Matricule: " + equipement.getMatricule() + "\n" +
                "Type: " + equipement.getTypeEquipement() + "\n" +
                "Marque: " + equipement.getMarqueEquipement() + "\n" +
                "Modèle: " + equipement.getModeleEquipement() + "\n" +
                "Numéro Série: " + equipement.getNumeroSerie() + "\n" +
                "Date Mise En Service: " + equipement.getDateMiseEnService() + "\n" +
                "Localisation: " + equipement.getLocalisation() + "\n" +
                "État: " + equipement.getEtat();

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

        LocalDate localDate = LocalDate.now();
        Date datePanne = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        panne.setDatePanne(datePanne);

        return equipementEnPanneRepository.save(panne);
    }

    @Override
    public List<EquipementEnPanne> getPannesByEquipement(Long equipementId) {
        return equipementEnPanneRepository.findByEquipement_IdEquipement(equipementId);
    }

    @Override
    public HistoriqueMouvement ajouterMouvement(Long equipementId, String typeMouvement) {
        EquipementInformatique equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new RuntimeException("Equipement non trouvé"));

        HistoriqueMouvement mouvement = new HistoriqueMouvement();
        mouvement.setEquipement(equipement);
        mouvement.setTypeMouvement(typeMouvement);

        LocalDate localDate = LocalDate.now();
        mouvement.setDateMouvement(localDate);

        return mouvementRepository.save(mouvement);
    }



    @Override
    public List<EquipementInformatique> getEquipementsByModele(String modele) {
        return equipementRepository.findByModeleEquipementContainingIgnoreCase(modele);
    }

    public long getTotalEquipement() {
        return equipementRepository.count();
    }

    public long getActiveEquipement() {
        return equipementRepository.countByEtatIgnoreCase("En Service");
    }

    public long getMaintenanceEquipement() {
        return equipementRepository.countByEtatIgnoreCase("En Maintenance");
    }

    public long getInactiveEquipement() {
        return equipementRepository.countByEtatIgnoreCase("Hors Service");
    }
    public Map<String, Map<String, Long>> getMonthlyStatusByYear(int year) {
        List<EquipementInformatique> all = equipementRepository.findAll();
        Map<String, Map<String, Long>> result = new HashMap<>();

        for (EquipementInformatique e : all) {
            if (e.getDateMiseEnService() == null) continue;

            Calendar cal = Calendar.getInstance();
            cal.setTime(e.getDateMiseEnService());
            int eYear = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;

            if (eYear != year) continue;

            String monthStr = String.format("%02d", month);
            result.putIfAbsent(monthStr, new HashMap<>());
            String etat = e.getEtat() != null ? e.getEtat() : "Inconnu";

            result.get(monthStr).merge(etat, 1L, Long::sum);
        }

        return result;
    }
    public Map<String, Long> getEquipementCountByType() {
        return equipementRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        EquipementInformatique::getTypeEquipement,
                        Collectors.counting()
                ));
    }
    public Map<String, Double> getFailureRateByBrand() {
        List<EquipementInformatique> all = equipementRepository.findAll();
        Map<String, Long> totalPerBrand = all.stream()
                .collect(Collectors.groupingBy(
                        EquipementInformatique::getMarqueEquipement,
                        Collectors.counting()
                ));

        Map<String, Long> pannePerBrand = all.stream()
                .filter(e -> e.getEquipementEnPannes() != null && !e.getEquipementEnPannes().isEmpty())
                .collect(Collectors.groupingBy(
                        EquipementInformatique::getMarqueEquipement,
                        Collectors.counting()
                ));

        Map<String, Double> failureRates = new HashMap<>();
        for (String brand : totalPerBrand.keySet()) {
            long total = totalPerBrand.get(brand);
            long pannes = pannePerBrand.getOrDefault(brand, 0L);
            failureRates.put(brand, total > 0 ? (pannes * 100.0) / total : 0.0);
        }

        return failureRates;
    }
    public Map<String, Long> getEquipementCountByLocation() {
        return equipementRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        EquipementInformatique::getLocalisation,
                        Collectors.counting()
                ));
    }
    public Map<String, Double> getAverageAgeByType() {
        LocalDate today = LocalDate.now();

        return equipementRepository.findAll().stream()
                .filter(e -> e.getDateMiseEnService() != null)
                .collect(Collectors.groupingBy(
                        EquipementInformatique::getTypeEquipement,
                        Collectors.averagingDouble(e -> {
                            LocalDate miseEnService = e.getDateMiseEnService()
                                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            return today.getYear() - miseEnService.getYear();
                        })
                ));
    }
    public Map<String, Long> getAcquisitionTrend(int year) {
        List<EquipementInformatique> all = equipementRepository.findAll();

        return all.stream()
                .filter(e -> e.getDateMiseEnService() != null)
                .filter(e -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(e.getDateMiseEnService());
                    return cal.get(Calendar.YEAR) == year;
                })
                .collect(Collectors.groupingBy(e -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(e.getDateMiseEnService());
                    int month = cal.get(Calendar.MONTH) + 1;
                    return String.format("%02d", month);
                }, Collectors.counting()));
    }
    @Override
    public List<EquipementInformatique> getEquipementsByFilters(String location, String type, Integer year) {
        return equipementRepository.findByFilters(location, type, year);
    }
}