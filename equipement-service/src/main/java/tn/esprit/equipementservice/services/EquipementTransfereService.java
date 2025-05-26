package tn.esprit.equipementservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.EquipementTransfere;
import tn.esprit.equipementservice.repositories.EquipementRepository;
import tn.esprit.equipementservice.repositories.EquipementTransfereRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementTransfereService implements IEquipementTransfere {

    @Autowired
    private EquipementTransfereRepository equipementTransfereRepository;

    @Autowired
    private EquipementRepository equipementRepository;

    // Ajouter un transfert
    @Override
    @Transactional
    public EquipementTransfere addEquipementTransfere(EquipementTransfere equipementTransfere) {
        return equipementTransfereRepository.save(equipementTransfere);
    }

    // Lire un transfert par ID
    @Override
    public Optional<EquipementTransfere> getEquipementTransfereById(Long id) {
        return equipementTransfereRepository.findById(id);
    }

    // Lire tous les transferts
    @Override
    public List<EquipementTransfere> getAllEquipementsTransferes() {
        return equipementTransfereRepository.findAll();
    }

    // Mettre à jour un transfert
    @Override
    @Transactional
    public EquipementTransfere updateEquipementTransfere(Long id, EquipementTransfere updatedTransfert) {
        Optional<EquipementTransfere> optionalTransfert = equipementTransfereRepository.findById(id);
        if (optionalTransfert.isEmpty()) {
            throw new IllegalArgumentException("Transfert avec l'ID " + id + " non trouvé.");
        }

        EquipementTransfere existingTransfert = optionalTransfert.get();

        // Mise à jour des propriétés
        existingTransfert.setAncienProprietaire(updatedTransfert.getAncienProprietaire());
        existingTransfert.setMatricule(updatedTransfert.getMatricule());
        existingTransfert.setDateTransfert(updatedTransfert.getDateTransfert());
        existingTransfert.setCommentaires(updatedTransfert.getCommentaires());

        if (updatedTransfert.getEquipement() != null) {
            Long equipementId = updatedTransfert.getEquipement().getIdEquipement();
            EquipementInformatique equipement = equipementRepository.findById(equipementId)
                    .orElseThrow(() -> new IllegalArgumentException("Équipement avec l'ID " + equipementId + " non trouvé."));
            existingTransfert.setEquipement(equipement);
        }

        return equipementTransfereRepository.save(existingTransfert);
    }

    // Supprimer un transfert
    @Override
    @Transactional
    public void deleteEquipementTransfere(Long id) {
        if (!equipementTransfereRepository.existsById(id)) {
            throw new IllegalArgumentException("Transfert avec l'ID " + id + " non trouvé.");
        }
        equipementTransfereRepository.deleteById(id);
    }

    @Override
    public List<EquipementTransfere> getEquipementsTransferesByEquipementId(Long equipementId) {
        return equipementTransfereRepository.findByEquipement_IdEquipement(equipementId);
    }
}
