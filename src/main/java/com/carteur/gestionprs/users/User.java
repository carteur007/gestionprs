package com.carteur.gestionprs.users;

import java.util.List;

import javax.persistence.*;

import com.carteur.gestionprs.grades.Grade;


@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "nom")
    private  String nom;
    @Column(name = "prenom")
    private  String prenom;
    @Column(name = "nomMere")
    private  String nomMere;
    @Column(name = "nomPere")
    private  String nomPere;
    @Column(name = "email")
    private  String email;
    @Column(name = "matricule")
    private  String matricule;
    @Column(name = "telephone")
    private  String telephone;
    @Column(name = "lieuNaissance")
    private  String lieuNaissance;
    @Column(name = "dateNaissance")
    private  String dateNaissance;
    @Column(name = "dateEntree")
    private  String dateEntree;
    @Column(name = "centreFormation")
    private  String centreFormation;
    @Column(name = "region")
    private  String region;
    @Column(name = "arrondissement")
    private  String arrondissement;  

    public User() {
        super();
    }
    public User(String nom, String prenom, String nomMere, 
        String nomPere, String email, String matricule, String telephone, 
        String lieuNaissance, String dateNaissance, String dateEntree, 
        String centreFormation, String region, String arrondissement) {
        super();
    }
    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return String return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return String return the nomMere
     */
    public String getNomMere() {
        return nomMere;
    }

    /**
     * @param nomMere the nomMere to set
     */
    public void setNomMere(String nomMere) {
        this.nomMere = nomMere;
    }

    /**
     * @return String return the nomPere
     */
    public String getNomPere() {
        return nomPere;
    }

    /**
     * @param nomPere the nomPere to set
     */
    public void setNomPere(String nomPere) {
        this.nomPere = nomPere;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * @param matricule the matricule to set
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * @return String return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return String return the lieuNaissance
     */
    public String getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * @param lieuNaissance the lieuNaissance to set
     */
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    /**
     * @return String return the dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * @return String return the dateEntree
     */
    public String getDateEntree() {
        return dateEntree;
    }

    /**
     * @param dateEntree the dateEntree to set
     */
    public void setDateEntree(String dateEntree) {
        this.dateEntree = dateEntree;
    }

    /**
     * @return String return the centreFormation
     */
    public String getCentreFormation() {
        return centreFormation;
    }

    /**
     * @param centreFormation the centreFormation to set
     */
    public void setCentreFormation(String centreFormation) {
        this.centreFormation = centreFormation;
    }

    /**
     * @return String return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return String return the arrondissement
     */
    public String getArrondissement() {
        return arrondissement;
    }

    /**
     * @param arrondissement the arrondissement to set
     */
    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }

}