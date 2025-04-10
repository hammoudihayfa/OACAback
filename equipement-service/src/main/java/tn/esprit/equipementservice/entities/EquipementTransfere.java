package tn.esprit.equipementservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
public class EquipementTransfere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementTransfere;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipement_id")
    private EquipementInformatique equipement;

    private Date dateTransfert;
    private String commentaires;
    public EquipementTransfere() {
    }

    public EquipementTransfere(Long idEquipementTransfere, EquipementInformatique equipement, Date dateTransfert, String commentaires) {
        this.idEquipementTransfere = idEquipementTransfere;
        this.equipement = equipement;
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
