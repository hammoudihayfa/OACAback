package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.DTO.ListeLogicielDTO;
import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.HistoriqueMouvement;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

public interface IEquipement {
    List<ListeLogicielDTO> getLogicielsParEquipement(Long equipementId) ;
    List<EquipementInformatique> getEquipementsByMatricule(int matricule) ;

    EquipementInformatique ajouterEquipement(EquipementInformatique equipement);
    List<EquipementInformatique> listeEquipements();
    Optional<EquipementInformatique> getEquipementById(Long numeroPatrimoine);

    EquipementInformatique updateEquipement(Long numeroPatrimoine, EquipementInformatique equipement);

    void deleteEquipement(Long numeroPatrimoine);
    BufferedImage genererQRCode(int matricule) throws Exception;
    // Déclaration d'une panne
    EquipementEnPanne declarePanne(Long equipementId, String descriptionPanne);

    // Historique des pannes
    List<EquipementEnPanne> getPannesByEquipement(Long equipementId);

    // Ajouter un mouvement d'équipement
    HistoriqueMouvement ajouterMouvement(Long equipementId, String typeMouvement);

    // Historique des mouvements
    List<HistoriqueMouvement> getHistoriqueMouvements(Long equipementId);


}
