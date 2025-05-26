package tn.esprit.equipementservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
public class EquipementTransfere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementTransfere;

    @ManyToOne
    @JoinColumn(name = "equipement_id")
    @JsonBackReference
    private EquipementInformatique equipement;

    private String ancienProprietaire;
    private String matricule;
    private Date dateTransfert;
    private String commentaires;

    public EquipementTransfere() {
    }

    public EquipementTransfere(Long idEquipementTransfere, EquipementInformatique equipement, String ancienProprietaire, String matricule, Date dateTransfert, String commentaires) {
        this.idEquipementTransfere = idEquipementTransfere;
        this.equipement = equipement;
        this.ancienProprietaire = ancienProprietaire;
        this.matricule = matricule;
        this.dateTransfert = dateTransfert;
        this.commentaires = commentaires;
    }

    public Long getIdEquipementTransfere() {
        return idEquipementTransfere;
    }

    public void setIdEquipementTransfere(Long idEquipementTransfere) {
        this.idEquipementTransfere = idEquipementTransfere;
    }

    // Getter et Setter pour equipement
    public EquipementInformatique getEquipement() {
        return equipement;
    }

    public void setEquipement(EquipementInformatique equipement) {
        this.equipement = equipement;
    }

    public Date getDateTransfert() {
        return dateTransfert;
    }

    public String getAncienProprietaire() {
        return ancienProprietaire;
    }


    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setAncienProprietaire(String ancienProprietaire) {
        this.ancienProprietaire = ancienProprietaire;
    }

    public void setDateTransfert(Date dateTransfert) {
        this.dateTransfert = dateTransfert;
    }

    // Getter et Setter pour commentaires
    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    // Méthode toString pour afficher les détails de l'objet
    @Override
    public String toString() {
        return "EquipementTransfere{" +
                "idEquipementTransfere=" + idEquipementTransfere +
                ", equipement=" + equipement +
                ", dateTransfert=" + dateTransfert +
                ", commentaires='" + commentaires + '\'' +
                '}';
    }



}
