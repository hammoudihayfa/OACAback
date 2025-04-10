package tn.esprit.equipementservice.DTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "logiciel-service", url = "http://localhost:8084/api")
public interface LogicielClient {
    @GetMapping("/logiciels-service/equipement/{idEquipement}")
    List<ListeLogicielDTO> getLogicielsByEquipement(@PathVariable("idEquipement") Long idEquipement);
}