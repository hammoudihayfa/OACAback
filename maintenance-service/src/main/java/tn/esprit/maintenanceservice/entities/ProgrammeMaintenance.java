package tn.esprit.maintenanceservice.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class ProgrammeMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgrammeMaintenance;
    private String nomProgramme;
    private String frequence;
    @Temporal(TemporalType.DATE)
    private Date dateDerniereExecution;
    private String procedureMaintenance;
    private String actionCorrective;
    private String description;
    private String responsable;
    private String etudeSecurite;
    @OneToMany(mappedBy = "programme", cascade = CascadeType.ALL)
    private List<Rapport> rapports;


    public ProgrammeMaintenance() {
    }

    public ProgrammeMaintenance(String nomProgramme, String frequence, Date dateDerniereExecution,
                                String procedureMaintenance, String actionCorrective,
                                String description, String responsable, String etudeSecurite) {
        this.nomProgramme = nomProgramme;
        this.frequence = frequence;
        this.dateDerniereExecution = dateDerniereExecution;
        this.procedureMaintenance = procedureMaintenance;
        this.actionCorrective = actionCorrective;
        this.description = description;
        this.responsable = responsable;
        this.etudeSecurite = etudeSecurite;
    }



    public Long getIdProgrammeMaintenance() {
        return idProgrammeMaintenance;
    }

    public void setIdProgrammeMaintenance(Long idProgrammeMaintenance) {
        this.idProgrammeMaintenance = idProgrammeMaintenance;
    }

    public String getNomProgramme() {
        return nomProgramme;
    }

    public void setNomProgramme(String nomProgramme) {
        this.nomProgramme = nomProgramme;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public Date getDateDerniereExecution() {
        return dateDerniereExecution;
    }

    public void setDateDerniereExecution(Date dateDerniereExecution) {
        this.dateDerniereExecution = dateDerniereExecution;
    }

    public String getProcedureMaintenance() {
        return procedureMaintenance;
    }

    public void setProcedureMaintenance(String procedureMaintenance) {
        this.procedureMaintenance = procedureMaintenance;
    }

    public String getActionCorrective() {
        return actionCorrective;
    }

    public void setActionCorrective(String actionCorrective) {
        this.actionCorrective = actionCorrective;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEtudeSecurite() {
        return etudeSecurite;
    }

    public void setEtudeSecurite(String etudeSecurite) {
        this.etudeSecurite = etudeSecurite;
    }
}
