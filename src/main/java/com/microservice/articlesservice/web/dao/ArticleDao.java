package com.microservice.articlesservice.web.dao;

import com.microservice.articlesservice.web.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {
    // public List<Article> findAll();
    Article findById(int id);
    // public Article save(Article article);

    List<Article> findByPrixGreaterThan(int prixLimit);
    List<Article> findByNomLike(String recherche);

    @Query("SELECT p FROM Article p WHERE p.prix > :prixLimit")
    List<Article> chercherUnArticleCher(@Param("prixLimit") int prix);
}
