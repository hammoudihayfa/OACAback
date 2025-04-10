package tn.esprit.equipementservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class EtatEquipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtatEquipement;

    private String etatEquipementValue;
    private Date dateEtat;
    private int matriculeProprietaire;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "equipement_id", referencedColumnName = "numeroPatrimoine")

    private EquipementInformatique equipement;

    public EtatEquipement() {}

    public EtatEquipement(String etatEquipementValue, Date dateEtat, int matriculeProprietaire, EquipementInformatique equipement) {
        this.etatEquipementValue = etatEquipementValue;
        this.dateEtat = dateEtat;
        this.matriculeProprietaire = matriculeProprietaire;
        this.equipement = equipement;
    }

    public Long getIdEtatEquipement() {
        return idEtatEquipement;
    }

    public void setIdEtatEquipement(Long idEtatEquipement) {
        this.idEtatEquipement = idEtatEquipement;
    }

    public String getEtatEquipementValue() {
        return etatEquipementValue;
    }

    public void setEtatEquipementValue(String etatEquipementValue) {
        this.etatEquipementValue = etatEquipementValue;
    }

    public Date getDateEtat() {
        return dateEtat;
    }

    public void setDateEtat(Date dateEtat) {
        this.dateEtat = dateEtat;
    }

    public int getMatriculeProprietaire() {
        return matriculeProprietaire;
    }

    public void setMatriculeProprietaire(int matriculeProprietaire) {
        this.matriculeProprietaire = matriculeProprietaire;
    }

    public EquipementInformatique getEquipement() {
        return equipement;
    }

    public void setEquipement(EquipementInformatique equipement) {
        this.equipement = equipement;
    }
}
