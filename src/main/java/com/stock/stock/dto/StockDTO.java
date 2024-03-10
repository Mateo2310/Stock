package com.stock.stock.dto;

import com.stock.stock.entities.Stock;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class StockDTO implements Serializable {
    private BigDecimal quantityAvailable;

    private BigDecimal quantityPendingEntry;

    private String articleName;

    private Integer articleCode;

    public StockDTO() {
    }

    public StockDTO(Stock stock) {
        this.quantityAvailable = stock.getQuantityAvailable();
        this.quantityPendingEntry = stock.getQuantityPendingEntry();
        this.articleName = stock.getArticle().getArticleName();
        this.articleCode = stock.getArticle().getArticleCode();
    }

    public StockDTO(BigDecimal quantityAvailable, BigDecimal quantityPendingEntry, String articleName) {
        this.quantityAvailable = quantityAvailable;
        this.quantityPendingEntry = quantityPendingEntry;
        this.articleName = articleName;
    }
}
