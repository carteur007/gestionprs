package com.carteur.gestionprs.missions;

import javax.persistence.Entity;

import com.carteur.gestionprs.users.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
@Entity
@Table(name = "USER_MISSION_MISIONS")
public class MisionsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "mision_id")
    private Mision mission;
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * @return long return the id
     */
    public long getId() {
        return id;
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
     * @return Mision return the mission
     */
    public Mision getMission() {
        return mission;
    }

    /**
     * @param mission the mission to set
     */
    public void setMission(Mision mission) {
        this.mission = mission;
    }

}
