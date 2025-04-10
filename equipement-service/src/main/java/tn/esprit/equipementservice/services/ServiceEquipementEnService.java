package tn.esprit.equipementservice.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.EquipementEnService;
import tn.esprit.equipementservice.repositories.EquipementEnServiceRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceEquipementEnService implements IEquipementEnService {
    @Autowired
    private EquipementEnServiceRepository equipementEnServiceRepository;
    @Override
    public EquipementEnService ajouterEquipementEnService(EquipementEnService equipementEnService) {
        return equipementEnServiceRepository.save(equipementEnService);
    }

    @Override
    public EquipementEnService modifierEquipementEnService(Long idEquipementEnService, EquipementEnService equipementEnService) {
        if (equipementEnServiceRepository.existsById(idEquipementEnService)) {
            equipementEnService.setIdEquipementEnService(idEquipementEnService);
            return equipementEnServiceRepository.save(equipementEnService);
        } else {
            throw new RuntimeException("Equipement en service non trouvé !");
        }
    }

    @Override
    public void supprimerEquipementEnService(Long idEquipementEnService) {
        if (equipementEnServiceRepository.existsById(idEquipementEnService)) {
            equipementEnServiceRepository.deleteById(idEquipementEnService);
        } else {
            throw new RuntimeException("Equipement en service non trouvé !");
        }

    }

    @Override
    public Optional<EquipementEnService> obtenirEquipementEnServiceParId(Long idEquipementEnService) {
        return equipementEnServiceRepository.findById(idEquipementEnService);
    }

    @Override
    public List<EquipementEnService> obtenirTousLesEquipementsEnService() {
        return equipementEnServiceRepository.findAll();
    }
}
