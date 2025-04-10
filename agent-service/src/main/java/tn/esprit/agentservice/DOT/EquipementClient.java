package tn.esprit.agentservice.DOT;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "equipement-service" , url= "http://localhost:8081/api/equipements")

public interface EquipementClient {
    @GetMapping("/agent/{matricule}")
    List<EquipementDTO> getEquipementsByAgent(@PathVariable int matricule);
}
