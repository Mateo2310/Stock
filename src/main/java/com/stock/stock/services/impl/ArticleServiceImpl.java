package com.stock.stock.services.impl;

import com.stock.stock.dto.ArticleDTO;
import com.stock.stock.dto.PageCriteria;
import com.stock.stock.dto.PaginatedList;
import com.stock.stock.entities.Article;
import com.stock.stock.repositories.IArticlesRepository;
import com.stock.stock.services.IArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    private final IArticlesRepository iArticlesRepository;

    public ArticleServiceImpl(IArticlesRepository iArticlesRepository) {
        this.iArticlesRepository = iArticlesRepository;
    }


    @Override
    public PaginatedList<ArticleDTO> getAllArticles(PageCriteria pageCriteria) {
        Pageable pageable = PageRequest.of(pageCriteria.getPage(), pageCriteria.getSize());
        Page<Article> allArticlesPageable = this.iArticlesRepository.findAll(pageable);
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
        return articles.stream()
                .map(ArticleDTO::new)
                .toList();
    }

    @Override
    public ArticleDTO getArticleForCode(Integer articleCode) {
        return new ArticleDTO(iArticlesRepository.findArticleByArticleCode(articleCode));
    }

    @Override
    public void createArticle(ArticleDTO articleDTO) {
        iArticlesRepository.save(new Article(articleDTO.getArticleName(), articleDTO.getArticleCode()));
    }

    @Override
    public Boolean editArticle(ArticleDTO articleDTO) {
        Article editArticulo = iArticlesRepository.findArticleByArticleCode(articleDTO.getArticleCode());
        if (editArticulo != null) {
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

        return false;
    }
}