package com.carteur.gestionprs.groupements;


import java.util.Set;

import javax.persistence.*;

import com.carteur.gestionprs.users.User;

@Entity
@Table(name = "GROUPEMENT")
public class Groupement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private String ville;
    private String nom;
    private String pseudo;
    
    @ManyToMany
    @JoinTable(name = "AFFECTATION", joinColumns = {
            @JoinColumn(name = "groupement_id")
    }, inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userGroupements;


    public Groupement() {
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
     * @return String return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return String return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
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
     * @return String return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    /**
     * @return Set<User> return the userGroupements
     */
    public Set<User> getUserGroupements() {
        return userGroupements;
    }

    /**
     * @param userGroupements the userGroupements to set
     */
    public void setUserGroupements(Set<User> userGroupements) {
        this.userGroupements = userGroupements;
    }

}
