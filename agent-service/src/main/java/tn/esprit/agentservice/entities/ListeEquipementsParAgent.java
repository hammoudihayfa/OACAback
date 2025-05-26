package tn.esprit.agentservice.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class ListeEquipementsParAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementParAgent;
    private String nature;
    private String marque;
    private String modele;
    private String nSerie;
    private String statutEquipement;
    private String utilisateur;
    private String direction;
    private Date datePos;


    public ListeEquipementsParAgent() {
    }

    public ListeEquipementsParAgent(Long idEquipementParAgent, String nature, String marque, String modele,
                                    String nSerie, String statutEquipement, String utilisateur,
                                    String direction, Date datePos) {
        this.idEquipementParAgent = idEquipementParAgent;
        this.nature = nature;
        this.marque = marque;
        this.modele = modele;
        this.nSerie = nSerie;
        this.statutEquipement = statutEquipement;
        this.utilisateur = utilisateur;
        this.direction = direction;
        this.datePos = datePos;


    }

    public Long getIdEquipementParAgent() {
        return idEquipementParAgent;
    }

    public void setIdEquipementParAgent(Long idEquipementParAgent) {
        this.idEquipementParAgent = idEquipementParAgent;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getNSerie() {
        return nSerie;
    }

    public void setNSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public String getStatutEquipement() {
        return statutEquipement;
    }

    public void setStatutEquipement(String statutEquipement) {
        this.statutEquipement = statutEquipement;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Date getDatePos() {
        return datePos;
    }

    public void setDatePos(Date datePos) {
        this.datePos = datePos;
    }


}
