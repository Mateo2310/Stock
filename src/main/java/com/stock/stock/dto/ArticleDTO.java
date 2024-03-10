package com.stock.stock.dto;

import com.stock.stock.entities.Article;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ArticleDTO implements Serializable {
    private String articleName;
    private Integer articleCode;

    public ArticleDTO(String articleName, Integer articleCode) {
        this.articleName = articleName;
        this.articleCode = articleCode;
    }

    public ArticleDTO(Article article) {
        this.articleName = article.getArticleName();
        this.articleCode = article.getArticleCode();
    }
}
