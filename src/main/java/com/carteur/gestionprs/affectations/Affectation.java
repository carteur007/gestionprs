package com.carteur.gestionprs.affectations;

import javax.persistence.*;

import com.carteur.gestionprs.groupements.Groupement;
import com.carteur.gestionprs.users.User;

@Entity
@Table(name = "AFFECTATION")
public class Affectation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String code;
    private String ville;
    private String origine;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "groupement_id")
    private Groupement groupement;

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getOrigine() {
        return origine;
    }
    public String getVille() {
        return ville;
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
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @param origine the origine to set
     */
    public void setOrigine(String origine) {
        this.origine = origine;
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

    /**
     * @return Groupement return the groupement
     */
    public Groupement getGroupement() {
        return groupement;
    }

    /**
     * @param groupement the groupement to set
     */
    public void setGroupement(Groupement groupement) {
        this.groupement = groupement;
    }

}
