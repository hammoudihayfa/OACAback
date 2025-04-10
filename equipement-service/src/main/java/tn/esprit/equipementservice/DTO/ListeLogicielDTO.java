package tn.esprit.equipementservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListeLogicielDTO {
    private Long idLogiciel;
    private String nomLogiciel;
    private String version;
    private String numeroLicence;
    private String contrat;
    private Date dateLimite;
}
