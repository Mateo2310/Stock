package com.stock.stock.services;

import com.stock.stock.dto.PageCriteria;
import com.stock.stock.dto.PaginatedList;
import com.stock.stock.dto.StockDTO;
import com.stock.stock.entities.Article;

public interface IStockService {
    PaginatedList<StockDTO> getAvailableStock(PageCriteria pageCriteria);
    StockDTO getStockByCodeArticle(Integer codeArticle);

    Boolean createStock(StockDTO stockDTO);

    Boolean editStock(StockDTO stockDTO);

    Boolean createStockWithArticle(StockDTO stockDTO, Article article);
}
