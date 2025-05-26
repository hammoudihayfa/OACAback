package tn.esprit.equipementservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.entities.EquipementInformatique;
import tn.esprit.equipementservice.entities.EquipementReforme;
import tn.esprit.equipementservice.repositories.EquipementReformeRepository;
import tn.esprit.equipementservice.repositories.EquipementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementEnReformeService implements IEquipementEnReforme {
    @Autowired
    private EquipementReformeRepository equipementReformeRepository;
    @Autowired
    private EquipementRepository equipementInformatiqueRepository;
    public EquipementReforme createEquipementReforme(EquipementReforme equipementReforme) {
        // Vérifiez si l'équipement est null avant d'essayer d'y accéder
        if (equipementReforme.getEquipement() == null) {
            throw new RuntimeException("L'équipement associé est null");
        }

        // Rechercher l'équipement dans la base de données
        Optional<EquipementInformatique> equipementOptional = equipementInformatiqueRepository.findById(equipementReforme.getEquipement().getIdEquipement());

        if (equipementOptional.isPresent()) {
            // L'équipement existe, on l'associe à l'entité EquipementReforme
            equipementReforme.setEquipement(equipementOptional.get());
            return equipementReformeRepository.save(equipementReforme);
        } else {
            throw new RuntimeException("Equipement non trouvé");
        }
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
            EquipementInformatique equipementInformatique = equipementInformatiqueRepository.findById(equipementReforme.getEquipement().getIdEquipement())
                    .orElseThrow(() -> new RuntimeException("Équipement informatique non trouvé avec l'ID: " + equipementReforme.getEquipement().getIdEquipement()));

            equipementReforme.setEquipement(equipementInformatique);
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

    @Override
    public Optional<EquipementReforme> getEquipementReformeByNumeroPatrimoine(Long numeroPatrimoine) {
        return equipementReformeRepository.findByEquipementIdEquipement(numeroPatrimoine);

    }
}