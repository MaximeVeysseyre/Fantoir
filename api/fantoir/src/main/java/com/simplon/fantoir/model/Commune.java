package com.simplon.fantoir.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "commune")
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_commune;
    private String nom_commune;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commune", targetEntity = CodePostal.class)
    @Fetch(FetchMode.JOIN)
    private List<CodePostal> code_postaux;

    public Commune(){

    }

    public Commune(Long id, String nom_commune){
        this.id_commune = id;
        this.nom_commune = nom_commune;
    }

    public Long getId_commune() {
        return id_commune;
    }

    public void setId_commune(Long id_commune) {
        this.id_commune = id_commune;
    }

    public String getNom_commune() {
        return nom_commune;
    }

    public void setNom_commune(String nom_commune) {
        this.nom_commune = nom_commune;
    }

    public List<CodePostal> getCode_postaux() {
        return code_postaux;
    }

    public void setCode_postaux(List<CodePostal> code_postaux) {
        this.code_postaux = code_postaux;
    }
}
