package tn.esprit.equipementservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.EquipementEnStock;
import tn.esprit.equipementservice.repositories.EquipementEnStockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementEnStockService implements IEquipementEnStock{
    @Autowired
    private EquipementEnStockRepository equipementEnStockRepository;
    @Override
    public EquipementEnStock createEquipement(EquipementEnStock equipement) {
        return equipementEnStockRepository.save(equipement);
    }

    @Override
    public Optional<EquipementEnStock> getEquipementById(Long idStock) {
        return equipementEnStockRepository.findById(idStock);
    }

    @Override
    public List<EquipementEnStock> getAllEquipements() {
        return equipementEnStockRepository.findAll();
    }

    @Override
    public EquipementEnStock updateEquipement(Long idStock, EquipementEnStock equipement) {
        if (equipementEnStockRepository.existsById(idStock)) {
            equipement.setIdStock(idStock);
            return equipementEnStockRepository.save(equipement);
        } else {
            throw new RuntimeException("Equipement not found with id: " + idStock);
        }
    }

    @Override
    public void deleteEquipement(Long idStock) {
        if (equipementEnStockRepository.existsById(idStock)) {
            equipementEnStockRepository.deleteById(idStock);
        } else {
            throw new RuntimeException("Equipement not found with id: " + idStock);
        }
    }
    }

