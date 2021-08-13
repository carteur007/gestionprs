package com.carteur.gestionprs.missions;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
public class Mision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String status;
    private String rapport;
    private String observation;
    private String dateDebut;
    private String dateFin;
    private String dateModification;
    private String titre;
    private String region;
    private String secteur;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private Set<MisionsUser> usermissions = new HashSet<>();

    public Mision() {
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
     * @return String return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return String return the rapport
     */
    public String getRapport() {
        return rapport;
    }

    /**
     * @param rapport the rapport to set
     */
    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    /**
     * @return String return the observation
     */
    public String getObservation() {
        return observation;
    }

    /**
     * @param observation the observation to set
     */
    public void setObservation(String observation) {
        this.observation = observation;
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
     * @return String return the dateModification
     */
    public String getDateModification() {
        return dateModification;
    }

    /**
     * @param dateModification the dateModification to set
     */
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }


    /**
     * @return Set<MisionsUser> return the usermissions
     */
    public Set<MisionsUser> getUsermissions() {
        return usermissions;
    }

    /**
     * @param usermissions the usermissions to set
     */
    public void setUsermissions(Set<MisionsUser> usermissions) {
        this.usermissions = usermissions;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }
}
