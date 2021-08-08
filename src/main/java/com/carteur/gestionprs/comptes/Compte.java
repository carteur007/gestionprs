package com.carteur.gestionprs.comptes;

import javax.persistence.*;

import com.carteur.gestionprs.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Compte extends SerializableSerializer{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String matricule;
    private String motPasse;
    private String telephone;
    private String fonction;

    @Lob
    @Column(nullable = true, length= 64)
    private byte[] photo;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @MapsId
    @JsonBackReference("u-cm")
    private User user;

    public Compte() {
        super();
    }
    public Compte(String matricule, String motPasse, String telephone, String fonction) {
        this.matricule = matricule;
        this.motPasse = motPasse;
        this.telephone = telephone;
        this.fonction = fonction;
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
     * @return String return the matricule
     */
    public String getMatricule() {
        return matricule;
    }
    /**
     * @param matricule the matricule to set
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    /**
     * @return String return the motPasse
     */
    public String getMotPasse() {
        return motPasse;
    }
    /**
     * @param motPasse the motPasse to set
     */
    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }
    /**
     * @return String return the telephone
     */
    public String getTelephone() {
        return telephone;
    }
    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    /**
     * @return String return the fonction
     */
    public String getFonction() {
        return fonction;
    }
    /**
     * @param fonction the fonction to set
     */
    public void setFonction(String fonction) {
        this.fonction = fonction;
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
     * @return ByteArray[] return the photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

}
