package com.stock.stock.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "stock")
@Getter
@Setter
@ToString(exclude = "id")
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private BigDecimal quantityAvailable;

    @NotNull
    private BigDecimal quantityPendingEntry;

    @OneToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Stock() {
    }

    public Stock(BigDecimal quantityAvailable, BigDecimal quantityPendingEntry, Article article) {
        this.quantityAvailable = quantityAvailable;
        this.quantityPendingEntry = quantityPendingEntry;
        this.article = article;
    }
}
