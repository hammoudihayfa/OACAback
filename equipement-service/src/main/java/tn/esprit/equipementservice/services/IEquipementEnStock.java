package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.entities.EquipementEnStock;

import java.util.List;
import java.util.Optional;

public interface IEquipementEnStock {
    EquipementEnStock createEquipement(EquipementEnStock equipement);


    Optional<EquipementEnStock> getEquipementById(Long idStock);
    List<EquipementEnStock> getAllEquipements();


    EquipementEnStock updateEquipement(Long idStock, EquipementEnStock equipement);


    void deleteEquipement(Long idStock);
}
