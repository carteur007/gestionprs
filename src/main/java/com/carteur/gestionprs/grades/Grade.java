package com.carteur.gestionprs.grades;

import javax.persistence.*;

import com.carteur.gestionprs.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String centre;
    private String titre;
    private String dateObtention;
    private String distinctiation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference("u-g")
    private User user;
    
    public Grade() {
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
     * @return String return the centre
     */
    public String getCentre() {
        return centre;
    }

    /**
     * @param centre the centre to set
     */
    public void setCentre(String centre) {
        this.centre = centre;
    }

    /**
     * @return String return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return String return the dateObtention
     */
    public String getDateObtention() {
        return dateObtention;
    }

    /**
     * @param dateObtention the dateObtention to set
     */
    public void setDateObtention(String dateObtention) {
        this.dateObtention = dateObtention;
    }

    /**
     * @return String return the distinctiation
     */
    public String getDistinctiation() {
        return distinctiation;
    }

    /**
     * @param distinctiation the distinctiation to set
     */
    public void setDistinctiation(String distinctiation) {
        this.distinctiation = distinctiation;
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
