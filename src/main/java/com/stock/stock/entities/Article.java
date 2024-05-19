package com.stock.stock.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "article")
@Getter
@Setter
@ToString(exclude = "id")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    private String articleName;

    @NotNull
    private Integer articleCode;

    public Article() {
    }

    public Article(String articleName, Integer articleCode) {
        System.out.println("code: " + articleCode);
        this.articleName = articleName;
        this.articleCode = articleCode;
    }
}
