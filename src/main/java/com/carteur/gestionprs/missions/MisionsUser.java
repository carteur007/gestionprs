package com.carteur.gestionprs.missions;

import javax.persistence.Entity;

import com.carteur.gestionprs.users.User;

import javax.persistence.*;
@Entity
public class MisionsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /* Provisoirement*/
    private int user;
    private int mission;

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
     * @return int return the user
     */
    public int getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(int user) {
        this.user = user;
    }

    /**
     * @return int return the mission
     */
    public int getMission() {
        return mission;
    }

    /**
     * @param mission the mission to set
     */
    public void setMission(int mission) {
        this.mission = mission;
    }

}
