package com.stock.stock.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

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

    @NotNull
    private Date dueDate;

    @NotNull
    private Date createdAt;

    @NotNull
    private Date updatedAt;

    public Article() {
    }

    public Article(String articleName, Integer articleCode, Date dueDate, Boolean isNew) {
        this.articleName = articleName;
        this.articleCode = articleCode;
        this.dueDate = dueDate;
        if (isNew) {
            this.createdAt = new Date();
        }
        this.updatedAt = new Date();
    }
}
