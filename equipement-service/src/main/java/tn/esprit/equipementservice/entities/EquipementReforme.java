package tn.esprit.equipementservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
public class EquipementReforme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementReforme;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "equipement_id")
    private EquipementInformatique equipement;

    private Date dateReforme;
    private String description;

    public EquipementReforme() {
    }

    public EquipementReforme(Long idEquipementReforme, EquipementInformatique equipement, Date dateReforme, String description) {
        this.idEquipementReforme = idEquipementReforme;
        this.equipement = equipement;
        this.dateReforme = dateReforme;
        this.description = description;
    }

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

    // ToString
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
