package tn.esprit.equipementservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
public class EquipementEnStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    private String typeEquipement;
    private Long quantite;
    private Long alerteStock;


    public EquipementEnStock() {
    }

    public EquipementEnStock(Long idStock, String typeEquipement, Long quantite, Long alerteStock) {
        this.idStock = idStock;
        this.typeEquipement = typeEquipement;
        this.quantite = quantite;
        this.alerteStock = alerteStock;
    }

    // Getters and Setters...
    public Long getIdStock() {
        return idStock;
    }

    public void setIdStock(Long idStock) {
        this.idStock = idStock;
    }

    public String getTypeEquipement() {
        return typeEquipement;
    }

    public void setTypeEquipement(String typeEquipement) {
        this.typeEquipement = typeEquipement;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }

    public Long getAlerteStock() {
        return alerteStock;
    }

    public void setAlerteStock(Long alerteStock) {
        this.alerteStock = alerteStock;
    }
}