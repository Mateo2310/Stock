package com.stock.stock.services;

import com.stock.stock.dto.ArticleDTO;
import com.stock.stock.dto.PageCriteria;
import com.stock.stock.dto.PaginatedList;

import java.util.List;

public interface IArticleService {
    PaginatedList<ArticleDTO> getAllArticles(PageCriteria pageCriteria);
    List<ArticleDTO> getArticlesForName(String articleName);
    ArticleDTO getArticleForCode(Integer articleCode);
    void createArticle(ArticleDTO articleDTO);
    Boolean editArticle(ArticleDTO articleDTO);
}
