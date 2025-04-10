package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.entities.EquipementRepartition;

import java.util.List;
import java.util.Optional;

public interface IEquipementRepartition {
    EquipementRepartition createEquipementRepartition(EquipementRepartition equipementRepartition);

    List<EquipementRepartition> getAllEquipementsRepartition();

    Optional<EquipementRepartition> getEquipementRepartitionById(Long idEquipementRepartition);

    EquipementRepartition updateEquipementRepartition(Long idEquipementRepartition, EquipementRepartition equipementRepartition);

    void deleteEquipementRepartition(Long idEquipementRepartition);
}
