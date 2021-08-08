package com.carteur.gestionprs.users;

import java.util.*;

import javax.persistence.*;

import com.carteur.gestionprs.affectations.Affectation;
import com.carteur.gestionprs.caissiers.Caissier;
import com.carteur.gestionprs.comptes.Compte;
import com.carteur.gestionprs.formations.Formation;
import com.carteur.gestionprs.grades.Grade;
import com.carteur.gestionprs.missions.MisionsUser;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "USER")
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    @JsonManagedReference("u-cm")
    private Compte compte;

    @JsonManagedReference("u-g")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Grade> grades = new ArrayList<>();

    @JsonManagedReference("u-ca")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Caissier> caissiers = new ArrayList<>();

    @JsonManagedReference("u-f")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Formation> formations = new ArrayList<>();

    @JsonManagedReference("u-mu")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MisionsUser> usermissions = new ArrayList<>();

    @JsonManagedReference("u-a")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Affectation> affectations = new ArrayList<>();

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
     * @param id the id to List
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
     * @param nom the nom to List
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
     * @param prenom the prenom to List
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
     * @param nomMere the nomMere to List
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
     * @param nomPere the nomPere to List
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
     * @param email the email to List
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
     * @param matricule the matricule to List
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
     * @param telephone the telephone to List
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
     * @param lieuNaissance the lieuNaissance to List
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
     * @param dateNaissance the dateNaissance to List
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
     * @param dateEntree the dateEntree to List
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
     * @param centreFormation the centreFormation to List
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
     * @param region the region to List
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
     * @param arrondissement the arrondissement to List
     */
    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }


    /**
     * @return Compte return the compte
     */
    public Compte getCompte() {
        return compte;
    }

    /**
     * @param compte the compte to List
     */
    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    /**
     * @return List<Grade> return the grades
     */
    public List<Grade> getGrades() {
        return grades;
    }

    /**
     * @param grades the grades to List
     */
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    /**
     * @return List<Caissier> return the caissiers
     */
    public List<Caissier> getCaissiers() {
        return caissiers;
    }

    /**
     * @param caissiers the caissiers to List
     */
    public void setCaissiers(List<Caissier> caissiers) {
        this.caissiers = caissiers;
    }

    /**
     * @return List<Formation> return the formations
     */
    public List<Formation> getFormations() {
        return formations;
    }

    /**
     * @param formations the formations to List
     */
    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }
    /**
     * @return List<MisionsUser> return the usermissions
     */
    public List<MisionsUser> getUsermissions() {
        return usermissions;
    }

    /**
     * @param usermissions the usermissions to List
     */
    public void setUsermissions(List<MisionsUser> usermissions) {
        this.usermissions = usermissions;
    }

    /**
     * @return List<Affectation> return the affectations
     */
    public List<Affectation> getAffectations() {
        return affectations;
    }

    /**
     * @param affectations the affectations to List
     */
    public void setAffectations(List<Affectation> affectations) {
        this.affectations = affectations;
    }

}