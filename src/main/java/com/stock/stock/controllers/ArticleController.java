package com.stock.stock.controllers;

import com.stock.stock.dto.ArticleDTO;
import com.stock.stock.dto.CustomErrorResponseDTO;
import com.stock.stock.dto.PageCriteria;
import com.stock.stock.exception.ResourceNotFoundException;
import com.stock.stock.services.IArticleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private final IArticleService iArticleService;

    public ArticleController(IArticleService iArticleService) {
        this.iArticleService = iArticleService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getArticle(@RequestBody @Valid PageCriteria pageCriteria) {
        log.info("Request to get all articles");
        try {
            return ResponseEntity.ok(iArticleService.getAllArticles(pageCriteria));
        } catch (ResourceNotFoundException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("message", e.getMessage());
            errors.put("errorClass", e.getErrorClass());
            CustomErrorResponseDTO customErrorResponseDTO = new CustomErrorResponseDTO(
                    e.getErrorCode(),
                    errors,
                    "313224234"
            );
            return new ResponseEntity<>(customErrorResponseDTO, HttpStatus.valueOf(e.getErrorCode()));
        }
    }

    @GetMapping("/findByArticleName/{articleName}")
    public ResponseEntity<?> getArticleForArticleName(@PathVariable String articleName) {
        log.info("Request to get article for article name");
        try {
            return ResponseEntity.ok(this.iArticleService.getArticlesForName(articleName));
        } catch (ResourceNotFoundException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("message", e.getMessage());
            errors.put("errorClass", e.getErrorClass());
            CustomErrorResponseDTO customErrorResponseDTO = new CustomErrorResponseDTO(
                    e.getErrorCode(),
                    errors,
                    "313224234"
            );
            return new ResponseEntity<>(customErrorResponseDTO, HttpStatus.valueOf(e.getErrorCode()));
        }
    }

    @PostMapping("/createArticle")
    public ResponseEntity<?> createArticle(@RequestBody @Valid ArticleDTO article) {
        log.info("Request create an article");
        try {
            this.iArticleService.createArticle(article);
            return ResponseEntity.ok("Articulo creado con exito");
        } catch (ResourceNotFoundException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("message", e.getMessage());
            errors.put("errorClass", e.getErrorClass());
            CustomErrorResponseDTO customErrorResponseDTO = new CustomErrorResponseDTO(
                    e.getErrorCode(),
                    errors,
                    "313224234"
            );
            return new ResponseEntity<>(customErrorResponseDTO, HttpStatus.valueOf(e.getErrorCode()));
        }
    }

    @PutMapping("/editArticle")
    public ResponseEntity<?> editArticle(@RequestBody @Valid ArticleDTO article) {
        log.info("Request edit an article");
        try {
            Boolean editArticleSuccess = this.iArticleService.editArticle(article);
            if (editArticleSuccess) {
                return ResponseEntity.ok("Articulo creado con exito");
            }

            return ResponseEntity.badRequest().build();
        } catch (ResourceNotFoundException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("message", e.getMessage());
            errors.put("errorClass", e.getErrorClass());
            CustomErrorResponseDTO customErrorResponseDTO = new CustomErrorResponseDTO(
                    e.getErrorCode(),
                    errors,
                    "313224234"
            );
            return new ResponseEntity<>(customErrorResponseDTO, HttpStatus.valueOf(e.getErrorCode()));
        }
    }
}
