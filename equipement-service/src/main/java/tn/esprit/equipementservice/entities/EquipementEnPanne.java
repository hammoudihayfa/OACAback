package tn.esprit.equipementservice.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class EquipementEnPanne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementEnPanne;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "equipement_id")
    private EquipementInformatique equipement;

    private Date datePanne;
    private String description;
    private String statut;
    private int matriculeDeclarant;
    private Long idProgrammeMaintenance;
    public Long getIdEquipementEnPanne() {
        return idEquipementEnPanne;
    }

    public void setIdEquipementEnPanne(Long idEquipementEnPanne) {
        this.idEquipementEnPanne = idEquipementEnPanne;
    }


    public EquipementInformatique getEquipement() {
        return equipement;
    }

    public void setEquipement(EquipementInformatique equipement) {
        this.equipement = equipement;
    }

    public String getNomEquipement() {
        return equipement != null ? equipement.getNom() : null;
    }

    public Date getDatePanne() {
        return datePanne;
    }

    public void setDatePanne(Date datePanne) {
        this.datePanne = datePanne;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getMatriculeDeclarant() {
        return matriculeDeclarant;
    }

    public void setMatriculeDeclarant(int matriculeDeclarant) {
        this.matriculeDeclarant = matriculeDeclarant;
    }

    public Long getIdProgrammeMaintenance() {
        return idProgrammeMaintenance;
    }

    public void setIdProgrammeMaintenance(Long idProgrammeMaintenance) {
        this.idProgrammeMaintenance = idProgrammeMaintenance;
    }
}
