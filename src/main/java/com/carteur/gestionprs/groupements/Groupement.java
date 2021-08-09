package com.carteur.gestionprs.groupements;


import java.util.*;

import javax.persistence.*;

import com.carteur.gestionprs.affectations.Affectation;
import com.carteur.gestionprs.legions.Legion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference("l-g")
    private Legion legion;

    @JsonManagedReference("g-a")
    @OneToMany(mappedBy = "groupement", cascade = CascadeType.ALL)
    private List<Affectation> affectations = new ArrayList<>();

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
     * @param id the id to List
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
     * @param code the code to List
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
     * @param ville the ville to List
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
     * @param nom the nom to List
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
     * @param pseudo the pseudo to List
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    /**
     * @return Legion return the legion
     */
    public Legion getLegion() {
        return legion;
    }

    /**
     * @param legion the legion to List
     */
    public void setLegion(Legion legion) {
        this.legion = legion;
    }
    /*
     * @return List<Affectation>
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
