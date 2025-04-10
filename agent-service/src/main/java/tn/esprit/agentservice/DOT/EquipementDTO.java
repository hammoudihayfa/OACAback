package tn.esprit.agentservice.DOT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class EquipementDTO {
    private Long numeroPatrimoine;
    private int matricule;
    private String typeEquipement;
    private String marqueEquipement;
    private String modeleEquipement;
    private String numeroSerie;
    private Date dateMiseEnService;
}
