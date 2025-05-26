package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.entities.EquipementTransfere;

import java.util.List;
import java.util.Optional;

public interface IEquipementTransfere {
    EquipementTransfere addEquipementTransfere(EquipementTransfere equipementTransfere);

    Optional<EquipementTransfere> getEquipementTransfereById(Long id);


    List<EquipementTransfere> getAllEquipementsTransferes();

    EquipementTransfere updateEquipementTransfere(Long id, EquipementTransfere equipementTransfere);

    void deleteEquipementTransfere(Long id);

    List<EquipementTransfere> getEquipementsTransferesByEquipementId(Long equipementId);
}
