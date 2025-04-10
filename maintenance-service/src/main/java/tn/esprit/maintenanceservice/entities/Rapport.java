package tn.esprit.maintenanceservice.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Rapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRapport;

    private String titre;
    private String contenu;
    private LocalDate dateCreation;

    @ManyToOne
    @JoinColumn(name = "programme_id_programme_maintenance")
    private ProgrammeMaintenance programme;


    public Rapport() {
    }

    public Rapport(Long idRapport, String titre, String contenu, LocalDate dateCreation, ProgrammeMaintenance programme) {
        this.idRapport = idRapport;
        this.titre = titre;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.programme = programme;
    }



    public Long getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(Long idRapport) {
        this.idRapport = idRapport;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ProgrammeMaintenance getProgramme() {
        return programme;
    }

    public void setProgramme(ProgrammeMaintenance programme) {
        this.programme = programme;
    }
}
