package tn.esprit.logicielservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class ListeLogiciel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLogiciel;
    private String nomLogiciel;
    private String version;
    private String numeroLicence;
    private String contrat;
    private Date dateLimite;
    private Long equipementId;

    public ListeLogiciel() {
    }

    public ListeLogiciel(String nomLogiciel, String version, String numeroLicence, String contrat, Date dateLimite, Long equipementId) {
        this.nomLogiciel = nomLogiciel;
        this.version = version;
        this.numeroLicence = numeroLicence;
        this.contrat = contrat;
        this.dateLimite = dateLimite;
        this.equipementId = equipementId;
    }

    public Long getIdLogiciel() {
        return idLogiciel;
    }

    public void setIdLogiciel(Long idLogiciel) {
        this.idLogiciel = idLogiciel;
    }

    public String getNomLogiciel() {
        return nomLogiciel;
    }

    public void setNomLogiciel(String nomLogiciel) {
        this.nomLogiciel = nomLogiciel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNumeroLicence() {
        return numeroLicence;
    }

    public void setNumeroLicence(String numeroLicence) {
        this.numeroLicence = numeroLicence;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }
    public Long getEquipementId() {
        return equipementId;
    }

    public void setEquipementId(Long equipementId) {
        this.equipementId = equipementId;
    }

}
