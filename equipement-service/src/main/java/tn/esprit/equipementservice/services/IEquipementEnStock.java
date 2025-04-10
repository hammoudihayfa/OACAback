package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.entities.EquipementEnStock;

import java.util.List;
import java.util.Optional;

public interface IEquipementEnStock {
    EquipementEnStock createEquipement(EquipementEnStock equipement);

    // Read
    Optional<EquipementEnStock> getEquipementById(Long idStock);
    List<EquipementEnStock> getAllEquipements();

    // Update
    EquipementEnStock updateEquipement(Long idStock, EquipementEnStock equipement);

    // Delete
    void deleteEquipement(Long idStock);
}
