package com.cgi.dtos;

import com.cgi.model.Role;

public class UserDto {
    private Long id;
    private String mail;
    private String password;
    private Role role;
    private String nom;
    private String prenom;

    
    public UserDto() {}

    
    public UserDto(Long id, String mail, String password, Role role, String nom, String prenom) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
