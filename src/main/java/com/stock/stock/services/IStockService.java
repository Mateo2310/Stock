package com.stock.stock.services;

import com.stock.stock.dto.PageCriteria;
import com.stock.stock.dto.PaginatedList;
import com.stock.stock.dto.StockDTO;

public interface IStockService {
    PaginatedList<StockDTO> getAvailableStock(PageCriteria pageCriteria);
    PaginatedList<StockDTO> getAvailableStockByArticle(String articleName);
}
