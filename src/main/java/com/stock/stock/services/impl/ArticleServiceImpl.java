package com.stock.stock.services.impl;

import com.stock.stock.dto.ArticleDTO;
import com.stock.stock.dto.PageCriteria;
import com.stock.stock.dto.PaginatedList;
import com.stock.stock.dto.StockDTO;
import com.stock.stock.entities.Article;
import com.stock.stock.exception.ResourceNotFoundException;
import com.stock.stock.repositories.IArticlesRepository;
import com.stock.stock.services.IArticleService;
import com.stock.stock.services.IStockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    private final IArticlesRepository iArticlesRepository;
    private final IStockService iStockService;

    public ArticleServiceImpl(IArticlesRepository iArticlesRepository, IStockService iStockService) {
        this.iArticlesRepository = iArticlesRepository;
        this.iStockService = iStockService;
    }


    @Override
    public PaginatedList<ArticleDTO> getAllArticles(PageCriteria pageCriteria) {
        Pageable pageable = PageRequest.of(pageCriteria.getPage(), pageCriteria.getSize());
        Page<Article> allArticlesPageable = this.iArticlesRepository.findAll(pageable);
        if (allArticlesPageable.isEmpty()) throw new ResourceNotFoundException("No articles found", "400");
        List<Article> listArticleAvailable = allArticlesPageable.stream().toList();
        List<ArticleDTO> listArticleAvailableDTO = listArticleAvailable.stream()
                .map(ArticleDTO::new)
                .toList();
        return new PaginatedList<>(listArticleAvailableDTO,
                allArticlesPageable.getTotalElements(),
                Long.parseLong(String.valueOf(pageCriteria.getSize())),
                Long.parseLong(String.valueOf(pageCriteria.getPage())));
    }

    @Override
    public List<ArticleDTO> getArticlesForName(String articleName) {
        List<Article> articles = iArticlesRepository.findArticlesByArticleName(articleName);
        if (articles == null || articles.isEmpty()) throw new ResourceNotFoundException("Articles with name "+articleName+" not found.", "200");
        return articles.stream()
                .map(ArticleDTO::new)
                .toList();
    }

    @Override
    public ArticleDTO getArticleForCode(Integer articleCode) {
        Article article = iArticlesRepository.findArticleByArticleCode(articleCode);
        if (article == null) throw new ResourceNotFoundException("The article with code "+articleCode+" not found.", "404");
        return new ArticleDTO(article);
    }

    @Override
    public void createArticle(ArticleDTO articleDTO) {
        Article articleCreated = iArticlesRepository.save(new Article(articleDTO.getArticleName(), articleDTO.getArticleCode()));
        if (articleDTO.getProductPendingEntry() == null) {
            articleDTO.setProductPendingEntry(BigDecimal.ZERO);
        }

        if (articleDTO.getProductQuantityAvailable() == null) {
            articleDTO.setProductQuantityAvailable(BigDecimal.ZERO);
        }

        iStockService.createStockWithArticle(
                new StockDTO(articleDTO.getProductQuantityAvailable(), articleDTO.getProductPendingEntry()),
                articleCreated);
    }

    @Override
    public Boolean editArticle(ArticleDTO articleDTO) {
        Article editArticulo = iArticlesRepository.findArticleByArticleCode(articleDTO.getArticleCode());
        if (editArticulo == null) throw new ResourceNotFoundException("The article with code "+articleDTO.getArticleName()+" not found.", "404")
        if (articleDTO.getArticleCode() != null && !articleDTO.getArticleCode().equals(editArticulo.getArticleCode())) {
            editArticulo.setArticleCode(articleDTO.getArticleCode());
        }

        if (articleDTO.getArticleName() != null
                && !articleDTO.getArticleName().isEmpty()
                && !articleDTO.getArticleName().equals(editArticulo.getArticleName())
        ) {
            editArticulo.setArticleName(articleDTO.getArticleName());
        }

        iArticlesRepository.save(editArticulo);
        return true;
    }
}
