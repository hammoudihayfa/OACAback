package tn.esprit.equipementservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity

@Data
public class EquipementEnService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementEnService;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "equipement_id")
    private EquipementInformatique equipement;

    private Date derniereDateOperationnel;
    public EquipementEnService() {}


    public EquipementEnService(Long idEquipementEnService, EquipementInformatique equipement, Date derniereDateOperationnel) {
        this.idEquipementEnService = idEquipementEnService;
        this.equipement = equipement;
        this.derniereDateOperationnel = derniereDateOperationnel;
    }

    public Long getIdEquipementEnService() {
        return idEquipementEnService;
    }

    public EquipementInformatique getEquipement() {
        return equipement;
    }

    public Date getDerniereDateOperationnel() {
        return derniereDateOperationnel;
    }


    public void setIdEquipementEnService(Long idEquipementEnService) {
        this.idEquipementEnService = idEquipementEnService;
    }

    public void setEquipement(EquipementInformatique equipement) {
        this.equipement = equipement;
    }

    public void setDerniereDateOperationnel(Date derniereDateOperationnel) {
        this.derniereDateOperationnel = derniereDateOperationnel;
    }

}
