package com.stock.stock.dto;

import com.stock.stock.entities.Stock;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class StockDTO implements Serializable {
    @NotNull(message = "quantity available shouldn't be null")
    @Min(0)
    private BigDecimal quantityAvailable;

    @NotNull(message = "quantity pending entry shouldn't be null")
    @Min(0)
    private BigDecimal quantityPendingEntry;

    @NotNull(message = "name article shouldn't be null")
    @NotEmpty(message = "name article shouldn't be empty")
    private String articleName;

    @NotNull(message = "code article shouldn't be null")
    @NotEmpty(message = "code article shouldn't be empty")
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

    public StockDTO(BigDecimal quantityAvailable, BigDecimal quantityPendingEntry) {
        this.quantityAvailable = quantityAvailable;
        this.quantityPendingEntry = quantityPendingEntry;
    }
}
