package com.stock.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PaginatedList<T> implements Serializable {
    private List<T> list;

    private Long totalRecords;

    private Long amountPerPage;

    private Long currentPage;

    public PaginatedList(List<T> list, Long totalRecords, Long amountPerPage, Long currentPage) {
        this.list = list;
        this.totalRecords = totalRecords;
        this.amountPerPage = amountPerPage;
        this.currentPage = currentPage;
    }
}
