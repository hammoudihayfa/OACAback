package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.entities.EquipementEnPanne;

import java.util.List;
import java.util.Optional;

public interface IEquipementEnPanne {
    EquipementEnPanne ajouterEquipementEnPanne(EquipementEnPanne equipementEnPanne);
    List<EquipementEnPanne> listeEquipementsEnPanne();

    Optional<EquipementEnPanne> getEquipementEnPanneById(Long idEquipementEnPane);

    EquipementEnPanne updateEquipementEnPanne(Long idEquipementEnPane, EquipementEnPanne equipementEnPanne);

    void deleteEquipementEnPanne(Long idEquipementEnPane);
    public double getGlobalFailureRate();
}
