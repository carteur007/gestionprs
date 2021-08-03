package com.carteur.gestionprs.missions;

import javax.persistence.Entity;

import com.carteur.gestionprs.users.User;

import javax.persistence.*;

import org.springframework.data.annotation.Id;

@Entity
public class MisionsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private User user;
    private Mision mission;

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
