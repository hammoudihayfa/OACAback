package tn.esprit.equipementservice.DTO;

import java.util.Date;

public class EquipementMaintenanceDTO {
    private Long idEquipementEnPanne;
    private String nomEquipement;
    private Long idProgrammeMaintenance;
    private String nomProgramme;
    private Date dateMaintenance;

    public EquipementMaintenanceDTO() {}

    public EquipementMaintenanceDTO(Long idEquipementEnPanne, String nomEquipement,
                                    Long idProgrammeMaintenance, String nomProgramme, Date dateMaintenance) {
        this.idEquipementEnPanne = idEquipementEnPanne;
        this.nomEquipement = nomEquipement;
        this.idProgrammeMaintenance = idProgrammeMaintenance;
        this.nomProgramme = nomProgramme;
        this.dateMaintenance = dateMaintenance;
    }

    public Long getIdEquipementEnPanne() { return idEquipementEnPanne; }
    public void setIdEquipementEnPanne(Long idEquipementEnPanne) { this.idEquipementEnPanne = idEquipementEnPanne; }

    public String getNomEquipement() { return nomEquipement; }
    public void setNomEquipement(String nomEquipement) { this.nomEquipement = nomEquipement; }

    public Long getIdProgrammeMaintenance() { return idProgrammeMaintenance; }
    public void setIdProgrammeMaintenance(Long idProgrammeMaintenance) { this.idProgrammeMaintenance = idProgrammeMaintenance; }

    public String getNomProgramme() { return nomProgramme; }
    public void setNomProgramme(String nomProgramme) { this.nomProgramme = nomProgramme; }

    public Date getDateMaintenance() { return dateMaintenance; }
    public void setDateMaintenance(Date dateMaintenance) { this.dateMaintenance = dateMaintenance; }

}
