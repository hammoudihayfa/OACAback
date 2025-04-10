package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.entities.EquipementReforme;

import java.util.List;
import java.util.Optional;

public interface IEquipementEnReforme {
    EquipementReforme createEquipementReforme(EquipementReforme equipementReforme);
    Optional<EquipementReforme> getEquipementReformeById(Long idEquipementReforme);
    List<EquipementReforme> getAllEquipementsReformes();
    EquipementReforme updateEquipementReforme(Long idEquipementReforme, EquipementReforme equipementReforme);
    void deleteEquipementReforme(Long idEquipementReforme);
}
