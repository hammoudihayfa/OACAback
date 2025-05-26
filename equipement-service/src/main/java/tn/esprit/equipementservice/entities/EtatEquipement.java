package tn.esprit.equipementservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Entity
public class EtatEquipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtatEquipement;

    private String etatEquipementValue;

    private LocalDate dateEtat;

    private Integer matriculeProprietaire;

    private String proprietaire;

    @ManyToOne
    @JoinColumn(name = "equipement_id")
    @JsonBackReference

    private EquipementInformatique equipement;

    public EtatEquipement() {
    }

    public EtatEquipement(Long idEtatEquipement, String etatEquipementValue, LocalDate dateEtat, Integer matriculeProprietaire, String proprietaire, EquipementInformatique equipement) {
        this.idEtatEquipement = idEtatEquipement;
        this.etatEquipementValue = etatEquipementValue;
        this.dateEtat = dateEtat;
        this.matriculeProprietaire = matriculeProprietaire;
        this.proprietaire = proprietaire;
        this.equipement = equipement;
    }
    // Getters et setters

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

    public LocalDate getDateEtat() {
        return dateEtat;
    }

    public void setDateEtat(LocalDate dateEtat) {
        this.dateEtat = dateEtat;
    }

    public Integer getMatriculeProprietaire() {
        return matriculeProprietaire;
    }

    public void setMatriculeProprietaire(Integer matriculeProprietaire) {
        this.matriculeProprietaire = matriculeProprietaire;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public EquipementInformatique getEquipement() {
        return equipement;
    }

    public void setEquipement(EquipementInformatique equipement) {
        this.equipement = equipement;
    }
}
