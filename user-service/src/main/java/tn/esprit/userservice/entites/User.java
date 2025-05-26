package tn.esprit.userservice.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String name;
     private String prenom;
private String email;
 private String aeroport;
 private String role;
 private String password;
    @Column(unique = true)
    private String phoneNumber;
    private String resetCode;
    public User() {
    }

    public User(Long idUser) {
        this.idUser = idUser;
    }

    public User(Long idUser, String name, String prenom, String email, String aeroport, String role, String password, String phoneNumber, String resetCode) {
        this.idUser = idUser;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.aeroport = aeroport;
        this.role = role;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.resetCode = resetCode;
    }

    // Getters et Setters
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getAeroport() {
        return aeroport;
    }

    public void setAeroport(String aeroport) {
        this.aeroport = aeroport;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
