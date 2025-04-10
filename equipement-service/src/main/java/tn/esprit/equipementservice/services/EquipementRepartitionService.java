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
        if (equipement != null && equipement.getNumeroPatrimoine() != null) {
            equipement = equipementInformatiqueRepository.findById(equipement.getNumeroPatrimoine())
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

    @Override
    public EquipementRepartition updateEquipementRepartition(Long idEquipementRepartition, EquipementRepartition equipementRepartition) {
        if (equipementRepartitionRepository.existsById(idEquipementRepartition)) {
            equipementRepartition.setIdEquipementRepartition(idEquipementRepartition);
            return equipementRepartitionRepository.save(equipementRepartition);
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
