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

    public HistoriqueMouvement(Long idMouvement) {
        this.idMouvement = idMouvement;
    }

    public HistoriqueMouvement() {
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
