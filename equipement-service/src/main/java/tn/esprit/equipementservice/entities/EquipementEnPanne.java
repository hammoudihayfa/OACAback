package tn.esprit.equipementservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class EquipementEnPanne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipementEnPanne;

    @ManyToOne
    @JoinColumn(name = "equipement_id")
    @JsonIgnore

    private EquipementInformatique equipement;

    private Date datePanne;
    private String description;
    private String statut;
    private String priorite;
    private String typeDePanne;
    private Long idProgrammeMaintenance;
    @OneToMany(mappedBy = "equipementPanne", cascade = CascadeType.ALL)
    private List<HistoriquePanne> historique = new ArrayList<>();

    public EquipementEnPanne() {
    }

    public EquipementEnPanne(Long idEquipementEnPanne, EquipementInformatique equipement, Date datePanne, String description, String statut, String priorite, String typeDePanne, Long idProgrammeMaintenance) {
        this.idEquipementEnPanne = idEquipementEnPanne;
        this.equipement = equipement;
        this.datePanne = datePanne;
        this.description = description;
        this.statut = statut;
        this.priorite = priorite;
        this.typeDePanne = typeDePanne;
        this.idProgrammeMaintenance = idProgrammeMaintenance;
    }

    // Getters and Setters...
    public Long getIdEquipementEnPanne() {
        return idEquipementEnPanne;
    }

    public void setIdEquipementEnPanne(Long idEquipementEnPanne) {
        this.idEquipementEnPanne = idEquipementEnPanne;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getTypeDePanne() {
        return typeDePanne;
    }

    public void setTypeDePanne(String typeDePanne) {
        this.typeDePanne = typeDePanne;
    }

    public EquipementInformatique getEquipement() {
        return equipement;
    }

    public void setEquipement(EquipementInformatique equipement) {
        this.equipement = equipement;
    }

    public Date getDatePanne() {
        return datePanne;
    }

    public void setDatePanne(Date datePanne) {
        this.datePanne = datePanne;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }


    public Long getIdProgrammeMaintenance() {
        return idProgrammeMaintenance;
    }

    public void setIdProgrammeMaintenance(Long idProgrammeMaintenance) {
        this.idProgrammeMaintenance = idProgrammeMaintenance;
    }

   
}