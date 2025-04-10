package tn.esprit.equipementservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipementInformatique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroPatrimoine;
    private int matricule;
    private String nom;
    private String typeEquipement;
    private String marqueEquipement;
    private String modeleEquipement;
    private String numeroSerie;
    private Date dateMiseEnService;

    public Long getNumeroPatrimoine() {
        return numeroPatrimoine;
    }

    public void setNumeroPatrimoine(Long numeroPatrimoine) {
        this.numeroPatrimoine = numeroPatrimoine;
    }


    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeEquipement() {
        return typeEquipement;
    }

    public void setTypeEquipement(String typeEquipement) {
        this.typeEquipement = typeEquipement;
    }

    public String getMarqueEquipement() {
        return marqueEquipement;
    }

    public void setMarqueEquipement(String marqueEquipement) {
        this.marqueEquipement = marqueEquipement;
    }


    public String getModeleEquipement() {
        return modeleEquipement;
    }

    public void setModeleEquipement(String modeleEquipement) {
        this.modeleEquipement = modeleEquipement;
    }


    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }


    public Date getDateMiseEnService() {
        return dateMiseEnService;
    }

    public void setDateMiseEnService(Date dateMiseEnService) {
        this.dateMiseEnService = dateMiseEnService;
    }
}


