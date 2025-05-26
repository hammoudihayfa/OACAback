package tn.esprit.equipementservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
@Entity
public class EquipementReforme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementReforme;

    private Date dateReforme;
    private String description;

    @ManyToOne
    @JoinColumn(name = "equipement_id")
    private EquipementInformatique equipement;



    public EquipementReforme() {
    }

    public EquipementReforme(Long idEquipementReforme, Date dateReforme, String description, EquipementInformatique equipement) {
        this.idEquipementReforme = idEquipementReforme;
        this.dateReforme = dateReforme;
        this.description = description;
        this.equipement = equipement;
    }

    // Getters and Setters...
    public Long getIdEquipementReforme() {
        return idEquipementReforme;
    }

    public void setIdEquipementReforme(Long idEquipementReforme) {
        this.idEquipementReforme = idEquipementReforme;
    }

    public EquipementInformatique getEquipement() {
        return equipement;
    }

    public void setEquipement(EquipementInformatique equipement) {
        this.equipement = equipement;
    }


    public Date getDateReforme() {
        return dateReforme;
    }

    public void setDateReforme(Date dateReforme) {
        this.dateReforme = dateReforme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EquipementReforme{" +
                "idEquipementReforme=" + idEquipementReforme +
                ", equipement=" + equipement +
                ", dateReforme=" + dateReforme +
                ", description='" + description + '\'' +
                '}';
    }
}