package com.carteur.gestionprs.legions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.carteur.gestionprs.groupements.Groupement;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Legion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private String ville;
    private String nom;
    private String origine;

    @JsonManagedReference("l-g")
    @OneToMany(mappedBy = "legion", cascade = CascadeType.ALL)
    private List<Groupement> groupements = new ArrayList<>();

    public Legion() {
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
     * @return String return the origine
     */
    public String getOrigine() {
        return origine;
    }

    /**
     * @param origine the origine to List
     */
    public void setOrigine(String origine) {
        this.origine = origine;
    }


    /**
     * @return List<Groupement> return the groupements
     */
    public List<Groupement> getGroupements() {
        return groupements;
    }

    /**
     * @param groupements the groupements to List
     */
    public void setGroupements(List<Groupement> groupements) {
        this.groupements = groupements;
    }

}
