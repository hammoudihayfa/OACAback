package tn.esprit.equipementservice.services;

import tn.esprit.equipementservice.entities.EquipementEnService;

import java.util.List;
import java.util.Optional;

public interface IEquipementEnService {
    EquipementEnService ajouterEquipementEnService(EquipementEnService equipementEnService);
    EquipementEnService modifierEquipementEnService(Long idEquipementEnService, EquipementEnService equipementEnService);
    void supprimerEquipementEnService(Long idEquipementEnService);
    Optional<EquipementEnService> obtenirEquipementEnServiceParId(Long idEquipementEnService);
    List<EquipementEnService> obtenirTousLesEquipementsEnService();
}
