package tn.esprit.equipementservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
@Entity
public class EquipementRepartition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementRepartition;

    @ManyToOne
    @JoinColumn(name = "equipement_id")
    private EquipementInformatique equipement;

    private String localisation;
    private String uniteResponsable;
    private Date dateDebut;
    private Date dateFin;

    public EquipementRepartition() {
    }

    public EquipementRepartition(Long idEquipementRepartition, EquipementInformatique equipement, String localisation, String uniteResponsable, Date dateDebut, Date dateFin) {
        this.idEquipementRepartition = idEquipementRepartition;
        this.equipement = equipement;
        this.localisation = localisation;
        this.uniteResponsable = uniteResponsable;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    // Getters and Setters...
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

}