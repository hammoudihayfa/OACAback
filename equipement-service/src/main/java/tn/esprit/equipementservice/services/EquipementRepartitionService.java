package tn.esprit.equipementservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.EquipementRepartition;
import tn.esprit.equipementservice.repositories.EquipementRepartitionRepository;
import tn.esprit.equipementservice.repositories.EquipementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementRepartitionService implements IEquipementRepartition{
    @Autowired
    private EquipementRepartitionRepository equipementRepartitionRepository;
    @Autowired
    private EquipementRepository equipementInformatiqueRepository;

    @Transactional
    public EquipementRepartition createEquipementRepartition(EquipementRepartition equipementRepartition) {
        EquipementInformatique equipement = equipementRepartition.getEquipement();
        if (equipement != null && equipement.getIdEquipement() != null) {
            equipement = equipementInformatiqueRepository.findById(equipement.getIdEquipement())
                    .orElseThrow(() -> new EntityNotFoundException("Equipement not found"));
        }
        return equipementRepartitionRepository.save(equipementRepartition);
    }

    @Override
    public List<EquipementRepartition> getAllEquipementsRepartition() {
        return equipementRepartitionRepository.findAll();
    }

    @Override
    public Optional<EquipementRepartition> getEquipementRepartitionById(Long idEquipementRepartition) {
        return equipementRepartitionRepository.findById(idEquipementRepartition);
    }

    public EquipementRepartition updateEquipementRepartition(Long id, EquipementRepartition updatedRepartition) {
        Optional<EquipementRepartition> existingRepartition = equipementRepartitionRepository.findById(id);
        if (existingRepartition.isPresent()) {
            EquipementRepartition repartitionToUpdate = existingRepartition.get();
            repartitionToUpdate.setEquipement(updatedRepartition.getEquipement());
            repartitionToUpdate.setLocalisation(updatedRepartition.getLocalisation());
            repartitionToUpdate.setUniteResponsable(updatedRepartition.getUniteResponsable());
            repartitionToUpdate.setDateDebut(updatedRepartition.getDateDebut());
            repartitionToUpdate.setDateFin(updatedRepartition.getDateFin());

            return equipementRepartitionRepository.save(repartitionToUpdate);
        }
        return null;
    }

    @Override
    public void deleteEquipementRepartition(Long idEquipementRepartition) {
        if (equipementRepartitionRepository.existsById(idEquipementRepartition)) {
            equipementRepartitionRepository.deleteById(idEquipementRepartition);
        }
    }

}
