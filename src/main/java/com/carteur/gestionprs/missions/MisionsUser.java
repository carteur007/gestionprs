package com.carteur.gestionprs.missions;

import javax.persistence.Entity;

import com.carteur.gestionprs.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
@Entity
@Table(name = "USER_MISSION_MISIONS")
public class MisionsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference("u-mu")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mision_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference("m-mu")
    private Mision mision;
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
     * @return Mision return the mision
     */
    public Mision getMision() {
        return mision;
    }

    /**
     * @param mision the mission to set
     */
    public void setMission(Mision mision) {
        this.mision = mision;
    }

}
