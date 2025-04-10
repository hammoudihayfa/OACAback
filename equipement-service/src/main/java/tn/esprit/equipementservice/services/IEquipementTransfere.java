package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.entities.EquipementTransfere;

import java.util.List;
import java.util.Optional;

public interface IEquipementTransfere {
    EquipementTransfere createEquipementTransfere(EquipementTransfere equipementTransfere);

    Optional<EquipementTransfere> getEquipementTransfereById(Long idEquipementTransfere);

    List<EquipementTransfere> getAllEquipementTransfere();

    EquipementTransfere updateEquipementTransfere(Long idEquipementTransfere, EquipementTransfere equipementTransfere);

    void deleteEquipementTransfere(Long idEquipementTransfere);
}
