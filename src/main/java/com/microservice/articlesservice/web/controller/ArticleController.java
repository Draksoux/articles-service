package com.microservice.articlesservice.web.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.microservice.articlesservice.web.dao.ArticleDao;
import com.microservice.articlesservice.web.model.Article;
import com.microservice.articlesservice.web.exceptions.ArticleIntrouvableException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Api("API pour les opérations CRUD sur les articles")
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    // Retourne tous les articles
    @RequestMapping(value="/Articles", method = RequestMethod.GET)
    public MappingJacksonValue listeArticles() {
        List<Article> articles = articleDao.findAll();
        SimpleBeanPropertyFilter monFiltre =
                SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        FilterProvider listDeNosFiltres = new
                SimpleFilterProvider().addFilter("monFiltreDynamique",
                monFiltre);
        MappingJacksonValue articlesFiltres = new
                MappingJacksonValue(articles);
        articlesFiltres.setFilters(listDeNosFiltres);
        return articlesFiltres;
    }

    // Retourne un article pour un id donné
    @ApiOperation(value = "Récupère un article grâce à son ID à condition que celui-ci soit en stock!")
    @RequestMapping(value = "/Articles/{id}", method =
            RequestMethod.GET)
    public Article afficherUnArticle(@PathVariable int id) {
        Article article = articleDao.findById(id);

        if (article==null) throw new ArticleIntrouvableException("L'articke avec l'id " + id + " est INTROUVABLE.");
        return article;
    }

    @GetMapping(value = "/test/articles/{prixLimit}")
    public List<Article> testeDeRequetes(@PathVariable int
                                                 prixLimit) {
        return articleDao.findByPrixGreaterThan(prixLimit);
    }

    @GetMapping(value = "/test/articles/like/{recherche}")
    public List<Article> testeDeRequetes(@PathVariable String
                                                 recherche) {
        return articleDao.findByNomLike("%"+recherche+"%");
    }

    // Ajoute un article et renvoit le bon code (201)
    @PostMapping(value = "/Articles")
    public ResponseEntity<Void> ajouterArticle(@RequestBody
                                                       Article article) {
        Article articleAdded = articleDao.save(article);
        if (articleAdded == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(articleAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping (value = "/Articles/{id}")
    public void supprimerArticle(@PathVariable int id) {
        articleDao.deleteById(id);
    }

    @PutMapping (value = "/Articles")
    public void updateArticle(@RequestBody Article article) {
        articleDao.save(article);
    }

    @GetMapping(value = "/test/articles/query/{prix}")
    public List<Article> chercherUnArticleCher(@PathVariable int
                                                 prix) {
        return articleDao.chercherUnArticleCher(prix);
    }

}
