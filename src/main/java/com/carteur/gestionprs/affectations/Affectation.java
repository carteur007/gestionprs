package com.carteur.gestionprs.affectations;

import javax.persistence.*;

@Entity
public class Affectation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String code;
    private String ville;
    private String origine;

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
}
