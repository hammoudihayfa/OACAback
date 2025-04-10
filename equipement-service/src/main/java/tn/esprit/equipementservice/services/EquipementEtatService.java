package tn.esprit.equipementservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.EtatEquipement;
import tn.esprit.equipementservice.repositories.EtatEquipementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementEtatService implements IEquipementEtat {

    @Autowired
    private EtatEquipementRepository etatEquipementRepository;

    @Override
    public EtatEquipement ajouterEtatEquipement(EtatEquipement etatEquipement) {
        return etatEquipementRepository.save(etatEquipement);
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
            etat.setEquipement(etatEquipements.getEquipement());

            return etatEquipementRepository.save(etat);
        }

        return null;
    }

    @Override
    public void supprimerEtatEquipement(Long idEtatEquipement) {
        etatEquipementRepository.deleteById(idEtatEquipement);
    }
}
