package tn.esprit.equipementservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class EquipementEnStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;
    private String nomStock;
    private String localisation;

    public EquipementEnStock() {
    }


    public EquipementEnStock(Long idStock, String nomStock, String localisation) {
        this.idStock = idStock;
        this.nomStock = nomStock;
        this.localisation = localisation;
    }

    public Long getIdStock() {
        return idStock;
    }

    public void setIdStock(Long idStock) {
        this.idStock = idStock;
    }

    public String getNomStock() {
        return nomStock;
    }

    public void setNomStock(String nomStock) {
        this.nomStock = nomStock;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public String toString() {
        return "EquipementEnStock{" +
                "idStock=" + idStock +
                ", nomStock='" + nomStock + '\'' +
                ", localisation='" + localisation + '\'' +
                '}';
    }

}
