package com.simplon.fantoir.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "code_postal")
public class CodePostal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_code_postal;

    private String code_postal;

    //private Long commune_id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="commune_id")
    @Fetch(FetchMode.JOIN)
    private Commune commune;

    public CodePostal(){

    }

    public CodePostal(Long id_code_postal, String code_postal, Commune commune) {
        this.id_code_postal = id_code_postal;
        this.code_postal = code_postal;
        this.commune = commune;
    }

    public Long getId_code_postal() {
        return id_code_postal;
    }

    public void setId_code_postal(Long id_code_postal) {
        this.id_code_postal = id_code_postal;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

}
