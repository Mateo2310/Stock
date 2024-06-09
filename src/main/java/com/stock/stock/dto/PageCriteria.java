package com.stock.stock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageCriteria {
    @NotNull(message = "page shouldn't be null")
    @Min(0)
    private Integer page;

    @Min(1)
    @NotNull(message = "page size shouldn't be null")
    private Integer size;

    public PageCriteria() {

    }

    public PageCriteria(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
