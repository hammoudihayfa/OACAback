package tn.esprit.equipementservice.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.repositories.EquipementEnPanneRepository;
import tn.esprit.equipementservice.repositories.EquipementRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EquipementPanneService implements IEquipementEnPanne{
    @Autowired
    private EquipementEnPanneRepository equipementEnPanneRepository;
    @Autowired
    private EquipementRepository equipementRepository;

    @Override
    public EquipementEnPanne ajouterEquipementEnPanne(EquipementEnPanne equipementEnPanne) {
        return equipementEnPanneRepository.save(equipementEnPanne);
    }

    @Override
    public List<EquipementEnPanne> listeEquipementsEnPanne() {
        return equipementEnPanneRepository.findAll();
    }

    @Override
    public Optional<EquipementEnPanne> getEquipementEnPanneById(Long idEquipementEnPane) {
        return equipementEnPanneRepository.findById(idEquipementEnPane);
    }

    @Override
    public EquipementEnPanne updateEquipementEnPanne(Long idEquipementEnPane, EquipementEnPanne equipementEnPanne) {
        if (equipementEnPanneRepository.existsById(idEquipementEnPane)) {
            equipementEnPanne.setIdEquipementEnPanne(idEquipementEnPane);
            return equipementEnPanneRepository.save(equipementEnPanne);
        }
        return null;
    }

    @Override
    public void deleteEquipementEnPanne(Long idEquipementEnPane) {
        equipementEnPanneRepository.deleteById(idEquipementEnPane);
    }
    public double getGlobalFailureRate() {
        long totalEquipements = equipementRepository.count();
        long totalEnPanne = equipementEnPanneRepository.count();

        if (totalEquipements == 0) return 0.0;

        return (double) totalEnPanne / totalEquipements;
    }
}
