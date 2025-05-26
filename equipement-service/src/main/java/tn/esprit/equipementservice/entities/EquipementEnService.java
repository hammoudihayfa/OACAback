package tn.esprit.equipementservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
public class EquipementEnService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementEnService;

    @OneToOne
    @JoinColumn(name = "equipement_id")
    @JsonIgnore
    @JsonProperty("equipement")

    private EquipementInformatique equipement;

    private String autresCaracteristiques;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    private Date derniereDateOperationnel;


    public EquipementEnService() {}

    public EquipementEnService(Long idEquipementEnService, EquipementInformatique equipement, String autresCaracteristiques, Date derniereDateOperationnel) {
        this.idEquipementEnService = idEquipementEnService;
        this.equipement = equipement;
        this.autresCaracteristiques = autresCaracteristiques;
        this.derniereDateOperationnel = derniereDateOperationnel;
    }

    // Getters and Setters...
    public Long getIdEquipementEnService() {
        return idEquipementEnService;
    }

    public void setIdEquipementEnService(Long idEquipementEnService) {
        this.idEquipementEnService = idEquipementEnService;
    }

    public EquipementInformatique getEquipement() {
        return equipement;
    }

    public void setEquipement(EquipementInformatique equipement) {
        this.equipement = equipement;
    }


    public String getAutresCaracteristiques() {
        return autresCaracteristiques;
    }

    public void setAutresCaracteristiques(String autresCaracteristiques) {
        this.autresCaracteristiques = autresCaracteristiques;
    }

    public Date getDerniereDateOperationnel() {
        return derniereDateOperationnel;
    }

    public void setDerniereDateOperationnel(Date derniereDateOperationnel) {
        this.derniereDateOperationnel = derniereDateOperationnel;
    }
}