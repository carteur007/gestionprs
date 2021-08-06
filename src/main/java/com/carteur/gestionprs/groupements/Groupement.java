package com.carteur.gestionprs.groupements;


import java.util.*;

import javax.persistence.*;

import com.carteur.gestionprs.affectations.Affectation;
import com.carteur.gestionprs.legions.Legion;
import com.carteur.gestionprs.users.User;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "legion_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Legion legion;

    @OneToMany(mappedBy = "groupement", cascade = CascadeType.ALL)
    private Set<Affectation> affectations = new HashSet<>();

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


    public Legion getLegion() {
        return legion;
    }

    public void setLegion(Legion legion) {
        this.legion = legion;
    }

    public Set<Affectation> getAffectations() {
        return affectations;
    }

    public void setAffectations(Set<Affectation> affectations) {
        this.affectations = affectations;
    }
}
