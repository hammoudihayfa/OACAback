package tn.esprit.equipementservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HistoriquePanne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;


    private String etat;

    private String commentaire;
    private String acteur;

    @ManyToOne
    @JoinColumn(name = "equipement_panne_id")
    private EquipementEnPanne equipementPanne;

    public HistoriquePanne() {
    }

    public HistoriquePanne(Long id, LocalDateTime date, String etat, String commentaire, String acteur) {
        this.id = id;
        this.date = date;
        this.etat = etat;
        this.commentaire = commentaire;
        this.acteur = acteur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActeur() {
        return acteur;
    }

    public void setActeur(String acteur) {
        this.acteur = acteur;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public EquipementEnPanne getEquipementPanne() {
        return equipementPanne;
    }

    public void setEquipementPanne(EquipementEnPanne equipementPanne) {
        this.equipementPanne = equipementPanne;
    }
}
