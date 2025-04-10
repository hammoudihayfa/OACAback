package tn.esprit.equipementservice.DTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "maintenance-service", url = "http://localhost:8083/api")

public interface MaintenanceClient {
    @GetMapping("/programme-maintenance/{id}")
    EquipementMaintenanceDTO getProgrammeMaintenance(@PathVariable Long id);
}
