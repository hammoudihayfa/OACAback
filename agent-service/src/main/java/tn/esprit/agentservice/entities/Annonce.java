package tn.esprit.agentservice.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnnonce;
    private String titre;

    private String description;
    private LocalDate datePublication;

    @ManyToOne
    private AgentNavigationAerienne agent;

    public Annonce() {
    }

    public Annonce(Long idAnnonce, String titre, String description, LocalDate datePublication, AgentNavigationAerienne agent) {
        this.idAnnonce = idAnnonce;
        this.titre = titre;
        this.description = description;
        this.datePublication = datePublication;
        this.agent = agent;
    }

    public Long getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Long idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public AgentNavigationAerienne getAgent() {
        return agent;
    }

    public void setAgent(AgentNavigationAerienne agent) {
        this.agent = agent;
    }
}
