package com.carteur.gestionprs.formations;


import javax.persistence.*;

import com.carteur.gestionprs.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String dateDebut;
    private String dateFin;
    private String typeFormation;
    private String diplome;
    private String centreFormation;
    private String corpsFormation;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference("u-f")
    private User user;

    public Formation() {
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
     * @return String return the dateDebut
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * @param dateDebut the dateDebut to set
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * @return String return the dateFin
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * @param dateFin the dateFin to set
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * @return String return the typeFormation
     */
    public String getTypeFormation() {
        return typeFormation;
    }

    /**
     * @param typeFormation the typeFormation to set
     */
    public void setTypeFormation(String typeFormation) {
        this.typeFormation = typeFormation;
    }

    /**
     * @return String return the diplome
     */
    public String getDiplome() {
        return diplome;
    }

    /**
     * @param diplome the diplome to set
     */
    public void setDiplome(String diplome) {
        this.diplome = diplome;
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
     * @return String return the corpsFormation
     */
    public String getCorpsFormation() {
        return corpsFormation;
    }

    /**
     * @param corpsFormation the corpsFormation to set
     */
    public void setCorpsFormation(String corpsFormation) {
        this.corpsFormation = corpsFormation;
    }


    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
