package tn.esprit.maintenanceservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class ProgrammeMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgrammeMaintenance;

    private String planAction;
    private String procedureMaintenance;
    private String actionCorrective;
    private String observation;
    private String responsable;
    private String etudeSecurite;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private FrequencyType frequencyType;

    private Integer estimatedDurationDays;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate nextDueDate;
    @OneToMany(mappedBy = "programme", cascade = CascadeType.ALL)
    private List<Rapport> rapports;
    public ProgrammeMaintenance() {
    }

    public ProgrammeMaintenance(Long idProgrammeMaintenance, String planAction, String procedureMaintenance, String actionCorrective, String observation, String responsable, String etudeSecurite, LocalDate startDate, FrequencyType frequencyType, Integer estimatedDurationDays, LocalDate nextDueDate) {
        this.idProgrammeMaintenance = idProgrammeMaintenance;
        this.planAction = planAction;
        this.procedureMaintenance = procedureMaintenance;
        this.actionCorrective = actionCorrective;
        this.observation = observation;
        this.responsable = responsable;
        this.etudeSecurite = etudeSecurite;
        this.startDate = startDate;
        this.frequencyType = frequencyType;
        this.estimatedDurationDays = estimatedDurationDays;
        this.nextDueDate = nextDueDate;
    }

    public Long getIdProgrammeMaintenance() {
        return idProgrammeMaintenance;
    }

    public void setIdProgrammeMaintenance(Long idProgrammeMaintenance) {
        this.idProgrammeMaintenance = idProgrammeMaintenance;
    }

    public String getPlanAction() {
        return planAction;
    }

    public void setPlanAction(String planAction) {
        this.planAction = planAction;
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    public Integer getEstimatedDurationDays() {
        return estimatedDurationDays;
    }

    public void setEstimatedDurationDays(Integer estimatedDurationDays) {
        this.estimatedDurationDays = estimatedDurationDays;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(LocalDate nextDueDate) {
        this.nextDueDate = nextDueDate;
    }
}
