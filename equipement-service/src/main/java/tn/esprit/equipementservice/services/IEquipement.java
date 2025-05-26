package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.DTO.ListeLogicielDTO;
import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.HistoriqueMouvement;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IEquipement {
    List<ListeLogicielDTO> getLogicielsParEquipement(Long equipementId) ;
    List<EquipementInformatique> getEquipementsByMatricule(int matricule) ;

    EquipementInformatique ajouterEquipement(EquipementInformatique equipement);
    List<EquipementInformatique> listeEquipements();
    Optional<EquipementInformatique> getEquipementById(Long numeroPatrimoine);

    EquipementInformatique updateEquipement(Long numeroPatrimoine, EquipementInformatique equipement);

    void deleteEquipement(Long numeroPatrimoine);
    public BufferedImage genererQRCode(String numeroSerie) throws Exception ;
    EquipementEnPanne declarePanne(Long equipementId, String descriptionPanne);

    List<EquipementEnPanne> getPannesByEquipement(Long equipementId);

    HistoriqueMouvement ajouterMouvement(Long equipementId, String typeMouvement);

    List<EquipementInformatique> getEquipementsByModele(String modele);


    long getTotalEquipement();
    long getActiveEquipement() ;

    long getMaintenanceEquipement() ;

    long getInactiveEquipement() ;
    Map<String, Map<String, Long>> getMonthlyStatusByYear(int year);
    Map<String, Long> getEquipementCountByType();
    Map<String, Double> getFailureRateByBrand();
    Map<String, Long> getEquipementCountByLocation();
    Map<String, Double> getAverageAgeByType();
    Map<String, Long> getAcquisitionTrend(int year);
    List<EquipementInformatique> getEquipementsByFilters(String location, String type, Integer year);


}