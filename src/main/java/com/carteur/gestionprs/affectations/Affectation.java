package com.carteur.gestionprs.affectations;

import javax.persistence.*;

import com.carteur.gestionprs.comptes.Compte;
import com.carteur.gestionprs.groupements.Groupement;
import com.carteur.gestionprs.users.User;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "compte_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Compte compte;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "groupement_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
