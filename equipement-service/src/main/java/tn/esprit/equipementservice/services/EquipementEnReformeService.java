package tn.esprit.equipementservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.EquipementReforme;
import tn.esprit.equipementservice.repositories.EquipementReformeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementEnReformeService implements IEquipementEnReforme{
    @Autowired
    private EquipementReformeRepository equipementReformeRepository;
    @Override
    public EquipementReforme createEquipementReforme(EquipementReforme equipementReforme) {
        return equipementReformeRepository.save(equipementReforme);
    }

    @Override
    public Optional<EquipementReforme> getEquipementReformeById(Long idEquipementReforme) {
        return equipementReformeRepository.findById(idEquipementReforme);
    }

    @Override
    public List<EquipementReforme> getAllEquipementsReformes() {
        return equipementReformeRepository.findAll();
    }

    @Override
    public EquipementReforme updateEquipementReforme(Long idEquipementReforme, EquipementReforme equipementReforme) {
        if (equipementReformeRepository.existsById(idEquipementReforme)) {
            equipementReforme.setIdEquipementReforme(idEquipementReforme);
            return equipementReformeRepository.save(equipementReforme);
        } else {
            throw new RuntimeException("Equipement reformé non trouvé avec l'ID: " + idEquipementReforme);
        }
    }

    @Override
    public void deleteEquipementReforme(Long idEquipementReforme) {
        if (equipementReformeRepository.existsById(idEquipementReforme)) {
            equipementReformeRepository.deleteById(idEquipementReforme);
        } else {
            throw new RuntimeException("Equipement reformé non trouvé avec l'ID: " + idEquipementReforme);
        }
    }
    }

