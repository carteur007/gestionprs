package com.carteur.gestionprs.caissiers;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.carteur.gestionprs.users.User;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Caissier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int status;
    private String motif;
    private String date;
    private String sanction;
    private String typeFacture;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User userCaissier;

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
     * @return int return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return String return the motif
     */
    public String getMotif() {
        return motif;
    }

    /**
     * @param motif the motif to set
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }

    /**
     * @return String return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return String return the sanction
     */
    public String getSanction() {
        return sanction;
    }

    /**
     * @param sanction the sanction to set
     */
    public void setSanction(String sanction) {
        this.sanction = sanction;
    }

    /**
     * @return String return the typeFacture
     */
    public String getTypeFacture() {
        return typeFacture;
    }

    /**
     * @param typeFacture the typeFacture to set
     */
    public void setTypeFacture(String typeFacture) {
        this.typeFacture = typeFacture;
    }

}
