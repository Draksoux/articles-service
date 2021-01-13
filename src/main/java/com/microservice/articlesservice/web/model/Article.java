package com.microservice.articlesservice.web.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@JsonFilter("monFiltreDynamique")
public class Article {

    @Id
    //GeneratedValue
    private int id;
    private String nom;
    private int prix;

    // info cachée au client
    private int prixAchat;

    public Article(int id, String nom, int prix, int prixAchat) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public Article() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}
