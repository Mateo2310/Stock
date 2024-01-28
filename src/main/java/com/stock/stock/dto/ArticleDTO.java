package com.stock.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ArticleDTO implements Serializable {
    private String articleName;
    private Integer articleCode;
}
