package com.stock.stock.dto;

import com.stock.stock.entities.Article;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ArticleDTO implements Serializable {
    @NotNull(message = "name article shouldn't be null")
    @NotEmpty(message = "name article shouldn't be empty")
    private String articleName;

    @NotNull(message = "code article shouldn't be null")
    private Integer articleCode;

    @NotNull
    @Min(0)
    private BigDecimal productQuantityAvailable;

    @NotNull
    @Min(0)
    private BigDecimal productPendingEntry;

    @NotNull
    private String dueDate;

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
