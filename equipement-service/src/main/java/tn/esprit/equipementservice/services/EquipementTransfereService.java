package tn.esprit.equipementservice.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.EquipementTransfere;
import tn.esprit.equipementservice.repositories.EquipementTransfereRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementTransfereService implements IEquipementTransfere{
    @Autowired
    private EquipementTransfereRepository equipementTransfereRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public EquipementTransfere createEquipementTransfere(EquipementTransfere equipementTransfere) {
        if (equipementTransfere.getEquipement() != null) {
            EquipementInformatique equipementInformatique = equipementTransfere.getEquipement();

            // Vérifier si l'équipement existe déjà en base
            if (equipementInformatique.getNumeroPatrimoine() != null) {
                // Si l'équipement existe, on le récupère et le lie
                equipementInformatique = entityManager.merge(equipementInformatique);
            } else {
                // Si l'équipement est neuf, on le persiste
                entityManager.persist(equipementInformatique);
            }

            // On lie l'équipement mis à jour ou persistant à l'entité EquipementTransfere
            equipementTransfere.setEquipement(equipementInformatique);
        }

        // Persister l'équipement transféré
        return equipementTransfereRepository.save(equipementTransfere);
    }



    @Override
    public Optional<EquipementTransfere> getEquipementTransfereById(Long idEquipementTransfere) {
        return equipementTransfereRepository.findById(idEquipementTransfere);
    }

    @Override
    public List<EquipementTransfere> getAllEquipementTransfere() {
        return equipementTransfereRepository.findAll();
    }

    @Override
    public EquipementTransfere updateEquipementTransfere(Long idEquipementTransfere, EquipementTransfere equipementTransfere) {
        if (equipementTransfereRepository.existsById(idEquipementTransfere)) {
            equipementTransfere.setIdEquipementTransfere(idEquipementTransfere);
            return equipementTransfereRepository.save(equipementTransfere);
        }
        return null;
    }

    @Override
    public void deleteEquipementTransfere(Long idEquipementTransfere) {
        if (equipementTransfereRepository.existsById(idEquipementTransfere)) {
            equipementTransfereRepository.deleteById(idEquipementTransfere);
        }
    }
}
