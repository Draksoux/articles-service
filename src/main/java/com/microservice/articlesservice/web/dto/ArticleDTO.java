package com.microservice.articlesservice.web.dto;

import com.microservice.articlesservice.web.model.Article;

public class ArticleDTO {

    private int id;
    private String nom;
    private int prix;
    private int prixAchat;
    private int marge;

    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.nom = article.getNom();
        this.prix = article.getPrix();
        this.prixAchat = article.getPrixAchat();
        this.marge = article.getPrix()-article.getPrixAchat();
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

    public int getMarge() {
        return marge;
    }
}
