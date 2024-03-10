package com.stock.stock.controllers;

import com.stock.stock.dto.ArticleDTO;
import com.stock.stock.dto.PageCriteria;
import com.stock.stock.services.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("/article")
public class ArticleController {
    private final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private final IArticleService iArticleService;

    public ArticleController(IArticleService iArticleService) {
        this.iArticleService = iArticleService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getArticle(PageCriteria pageCriteria) {
        log.info("Request to get all articles");
        try {
            return ResponseEntity.ok(iArticleService.getAllArticles(pageCriteria));
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "500 Internal Server Error");
            response.put("content", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByArticleName/{articleName}")
    public ResponseEntity<?> getArticleForArticleName(@RequestParam String articleName) {
        log.info("Request to get article for article name");
        try {
            return ResponseEntity.ok(iArticleService.getArticlesForName(articleName));
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "500 Internal Server Error");
            response.put("content", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createArticle")
    public ResponseEntity<?> createArticle(@RequestBody ArticleDTO article) {
        log.info("Request create an article");
        try {
            iArticleService.createArticle(article);
            return ResponseEntity.ok("Articulo creado con exito");
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "500 Internal Server Error");
            response.put("content", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editArticle")
    public ResponseEntity<?> editArticle(@RequestBody ArticleDTO article) {
        log.info("Request edit an article");
        try {
            Boolean editArticleSuccess = iArticleService.editArticle(article);
            if (editArticleSuccess) {
                return ResponseEntity.ok("Articulo creado con exito");
            }

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "500 Internal Server Error");
            response.put("content", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
