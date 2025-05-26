package tn.esprit.equipementservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.EtatEquipement;
import tn.esprit.equipementservice.repositories.EquipementRepository;
import tn.esprit.equipementservice.repositories.EtatEquipementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementEtatService implements IEquipementEtat {

    @Autowired
    private EtatEquipementRepository etatEquipementRepository;

    @Autowired
    private EquipementRepository equipementRepository;

    public EtatEquipement ajouterEtatEquipement(EtatEquipement etat) {
        Optional<EquipementInformatique> equipementOpt = equipementRepository.findById(etat.getEquipement().getIdEquipement());

        if (!equipementOpt.isPresent()) {
            throw new RuntimeException("Équipement avec ID " + etat.getEquipement().getIdEquipement() + " introuvable.");
        }

        etat.setEquipement(equipementOpt.get());
        return etatEquipementRepository.save(etat);
    }

    @Override
    public List<EtatEquipement> recupererTous() {
        return etatEquipementRepository.findAll();
    }

    @Override
    public Optional<EtatEquipement> recupererParId(Long idEtatEquipement) {
        return etatEquipementRepository.findById(idEtatEquipement);
    }

    @Override
    public EtatEquipement mettreAJourEtatEquipement(Long idEtatEquipement, EtatEquipement etatEquipements) {
        Optional<EtatEquipement> etatExistant = etatEquipementRepository.findById(idEtatEquipement);

        if (etatExistant.isPresent()) {
            EtatEquipement etat = etatExistant.get();
            etat.setEtatEquipementValue(etatEquipements.getEtatEquipementValue());
            etat.setDateEtat(etatEquipements.getDateEtat());
            etat.setMatriculeProprietaire(etatEquipements.getMatriculeProprietaire());
            etat.setProprietaire(etatEquipements.getProprietaire());

            Long equipementId = etatEquipements.getEquipement().getIdEquipement();
            Optional<EquipementInformatique> equipementOpt = equipementRepository.findById(equipementId);

            if (!equipementOpt.isPresent()) {
                throw new RuntimeException("Équipement avec ID " + equipementId + " introuvable.");
            }

            etat.setEquipement(equipementOpt.get());

            return etatEquipementRepository.save(etat);
        }

        return null;
    }


    @Override
    public void supprimerEtatEquipement(Long idEtatEquipement) {
        etatEquipementRepository.deleteById(idEtatEquipement);
    }
}
