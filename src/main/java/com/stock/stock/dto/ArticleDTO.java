package com.stock.stock.dto;

import com.stock.stock.entities.Article;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ArticleDTO implements Serializable {
    private String articleName;
    private Integer articleCode;
    private BigDecimal productQuantityAvailable;
    private BigDecimal productPendingEntry;

    public ArticleDTO(String articleName, Integer articleCode, BigDecimal productQuantityAvailable, BigDecimal productPendingEntry) {
        this.articleName = articleName;
        this.articleCode = articleCode;
        this.productQuantityAvailable = productQuantityAvailable;
        this.productPendingEntry = productPendingEntry;
    }

    public ArticleDTO(Article article) {
        this.articleName = article.getArticleName();
        this.articleCode = article.getArticleCode();
    }
}
