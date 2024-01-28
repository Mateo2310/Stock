package com.stock.stock.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageCriteria {
    @Min(0)
    private Integer page;

    @Min(1)
    private Integer size;

    public PageCriteria() {

    }

    public PageCriteria(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
