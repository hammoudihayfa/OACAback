package tn.esprit.equipementservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Data
public class EquipementRepartition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementRepartition;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "equipement_id")
    private EquipementInformatique equipement;

    private String localisation;
    private String uniteResponsable;
    private Date dateFin;

    public EquipementRepartition() {
    }

    public EquipementRepartition(Long idEquipementRepartition, EquipementInformatique equipement, String localisation, String uniteResponsable, Date dateFin) {
        this.idEquipementRepartition = idEquipementRepartition;
        this.equipement = equipement;
        this.localisation = localisation;
        this.uniteResponsable = uniteResponsable;
        this.dateFin = dateFin;
    }

    public Long getIdEquipementRepartition() {
        return idEquipementRepartition;
    }

    public void setIdEquipementRepartition(Long idEquipementRepartition) {
        this.idEquipementRepartition = idEquipementRepartition;
    }

    public EquipementInformatique getEquipement() {
        return equipement;
    }

    public void setEquipement(EquipementInformatique equipement) {
        this.equipement = equipement;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getUniteResponsable() {
        return uniteResponsable;
    }

    public void setUniteResponsable(String uniteResponsable) {
        this.uniteResponsable = uniteResponsable;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
