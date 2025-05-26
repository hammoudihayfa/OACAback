package tn.esprit.equipementservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.equipementservice.DTO.EquipementMaintenanceDTO;
import tn.esprit.equipementservice.DTO.MaintenanceClient;
import tn.esprit.equipementservice.entities.EquipementEnPanne;
import tn.esprit.equipementservice.repositories.EquipementEnPanneRepository;

import java.util.Optional;

@Service
public class EquipementMaintenanceService {
    @Autowired
    private EquipementEnPanneRepository equipementEnPanneRepository;

    @Autowired
    private MaintenanceClient maintenanceClient;

    public EquipementMaintenanceDTO getEquipementAvecMaintenance(Long idEquipement) {
        Optional<EquipementEnPanne> equipementOpt = equipementEnPanneRepository.findById(idEquipement);

        if (equipementOpt.isPresent()) {
            EquipementEnPanne equipement = equipementOpt.get();

            EquipementMaintenanceDTO maintenanceDTO = maintenanceClient.getProgrammeMaintenance(equipement.getIdProgrammeMaintenance());

            return new EquipementMaintenanceDTO(
                    equipement.getIdEquipementEnPanne(),
                    null,
                    maintenanceDTO.getIdProgrammeMaintenance(),
                    maintenanceDTO.getNomProgramme(),
                    maintenanceDTO.getDateMaintenance()
            );
        }

        return null;
    }

}
