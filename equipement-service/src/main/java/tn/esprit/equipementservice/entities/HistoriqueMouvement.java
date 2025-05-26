package tn.esprit.equipementservice.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HistoriqueMouvement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMouvement;

    @ManyToOne
    @JoinColumn(name = "equipement_id")
    private EquipementInformatique equipement;

    private String typeMouvement;
    private LocalDate dateMouvement;
    private String utilisateur;
    private String commentaire;




    public HistoriqueMouvement() {
    }

    public HistoriqueMouvement(Long idMouvement, EquipementInformatique equipement, String typeMouvement, LocalDate dateMouvement, String utilisateur, String commentaire) {
        this.idMouvement = idMouvement;
        this.equipement = equipement;
        this.typeMouvement = typeMouvement;
        this.dateMouvement = dateMouvement;
        this.utilisateur = utilisateur;
        this.commentaire = commentaire;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Long getIdMouvement() {
        return idMouvement;
    }


    public void setIdMouvement(Long idMouvement) {
        this.idMouvement = idMouvement;
    }

    public EquipementInformatique getEquipement() {
        return equipement;
    }

    public void setEquipement(EquipementInformatique equipement) {
        this.equipement = equipement;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public LocalDate getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(LocalDate dateMouvement) {
        this.dateMouvement = dateMouvement;
    }
}
