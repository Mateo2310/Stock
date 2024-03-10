package com.stock.stock.repositories;

import com.stock.stock.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticlesRepository extends JpaRepository<Article, Integer> {
    List<Article> findArticlesByArticleName(String articleName);
    Article findArticleByArticleCode(Integer articleCode);
}
