package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.entities.EtatEquipement;

import java.util.List;
import java.util.Optional;

public interface IEquipementEtat {
    EtatEquipement ajouterEtatEquipement(EtatEquipement etatEquipement);
    List<EtatEquipement> recupererTous();
    Optional<EtatEquipement> recupererParId(Long idEtatEquipement);
    EtatEquipement mettreAJourEtatEquipement(Long idEtatEquipement, EtatEquipement etatEquipements);
    void supprimerEtatEquipement(Long idEtatEquipement);
}
