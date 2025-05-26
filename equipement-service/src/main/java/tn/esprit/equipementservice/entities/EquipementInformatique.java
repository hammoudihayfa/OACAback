package tn.esprit.equipementservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class EquipementInformatique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    private int matricule;
    private String typeEquipement;
    private String marqueEquipement;
    private String modeleEquipement;
    @Column(nullable = false)
    private String localisation;

    private String numeroSerie;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateMiseEnService;

    private String etat;

    @OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @JsonManagedReference

    private List<EtatEquipement> etatEquipements = new ArrayList<>();

    @OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
    private List<EquipementEnPanne> equipementEnPannes = new ArrayList<>();

    @OneToMany(mappedBy = "equipement", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<HistoriqueMouvement> historiqueMouvements = new ArrayList<>();
    @OneToOne(mappedBy = "equipement", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private EquipementEnService equipementEnService;

    @OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<EquipementReforme> equipementReformes;

    @OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore

    private List<EquipementTransfere> equipementTransferes;

    @OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore

    private List<EquipementRepartition> equipementRepartitions;



    // Getters et Setters


    public Long getIdEquipement() {
        return idEquipement;
    }

    public void setIdEquipement(Long idEquipement) {
        this.idEquipement = idEquipement;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
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

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public List<EquipementReforme> getEquipementReformes() {
        return equipementReformes;
    }

    public void setEquipementReformes(List<EquipementReforme> equipementReformes) {
        this.equipementReformes = equipementReformes;
    }

    public List<EquipementTransfere> getEquipementTransferes() {
        return equipementTransferes;
    }

    public void setEquipementTransferes(List<EquipementTransfere> equipementTransferes) {
        this.equipementTransferes = equipementTransferes;
    }

    public List<EquipementRepartition> getEquipementRepartitions() {
        return equipementRepartitions;
    }

    public void setEquipementRepartitions(List<EquipementRepartition> equipementRepartitions) {
        this.equipementRepartitions = equipementRepartitions;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<EtatEquipement> getEtatEquipements() {
        return etatEquipements;
    }

    public void setEtatEquipements(List<EtatEquipement> etatEquipements) {
        this.etatEquipements = etatEquipements;
    }

    public List<EquipementEnPanne> getEquipementEnPannes() {
        return equipementEnPannes;
    }

    public void setEquipementEnPannes(List<EquipementEnPanne> equipementEnPannes) {
        this.equipementEnPannes = equipementEnPannes;
    }

    public List<HistoriqueMouvement> getHistoriqueMouvements() {
        return historiqueMouvements;
    }

    public void setHistoriqueMouvements(List<HistoriqueMouvement> historiqueMouvements) {
        this.historiqueMouvements = historiqueMouvements;
    }

    public EquipementEnService getEquipementEnService() {
        return equipementEnService;
    }

    public void setEquipementEnService(EquipementEnService equipementEnService) {
        this.equipementEnService = equipementEnService;
    }

    // Constructeurs

    public EquipementInformatique() {
    }

    public EquipementInformatique(Long idEquipement, int matricule, String typeEquipement, String marqueEquipement, String modeleEquipement, String localisation, String numeroSerie, Date dateMiseEnService, String etat, List<EtatEquipement> etatEquipements, List<EquipementEnPanne> equipementEnPannes, List<HistoriqueMouvement> historiqueMouvements, EquipementEnService equipementEnService, List<EquipementReforme> equipementReformes, List<EquipementTransfere> equipementTransferes, List<EquipementRepartition> equipementRepartitions) {
        this.idEquipement = idEquipement;
        this.matricule = matricule;
        this.typeEquipement = typeEquipement;
        this.marqueEquipement = marqueEquipement;
        this.modeleEquipement = modeleEquipement;
        this.localisation = localisation;
        this.numeroSerie = numeroSerie;
        this.dateMiseEnService = dateMiseEnService;
        this.etat = etat;
        this.etatEquipements = etatEquipements;
        this.equipementEnPannes = equipementEnPannes;
        this.historiqueMouvements = historiqueMouvements;
        this.equipementEnService = equipementEnService;
        this.equipementReformes = equipementReformes;
        this.equipementTransferes = equipementTransferes;
        this.equipementRepartitions = equipementRepartitions;
    }
}
