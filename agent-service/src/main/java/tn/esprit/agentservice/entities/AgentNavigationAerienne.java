package tn.esprit.agentservice.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class AgentNavigationAerienne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricule;
    private String nom;
    private String prenom;
    private String unite;
    private String fonction;
    private String email;
    private int cin;
    @OneToMany(mappedBy = "agent")
    private List<Annonce> annonces;
    public AgentNavigationAerienne() {
    }

    public AgentNavigationAerienne(String nom, String prenom, String unite, String fonction, String email, int cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.unite = unite;
        this.fonction = fonction;
        this.email = email;
        this.cin = cin;

    }

    public void setMatricule(Long matricule) {
        this.matricule = matricule;
    }

    public Long getMatricule() {
        return matricule;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }
}