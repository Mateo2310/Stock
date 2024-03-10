package com.stock.stock.services.impl;

import com.stock.stock.dto.PageCriteria;
import com.stock.stock.dto.PaginatedList;
import com.stock.stock.dto.StockDTO;
import com.stock.stock.entities.Article;
import com.stock.stock.entities.Stock;
import com.stock.stock.repositories.IArticlesRepository;
import com.stock.stock.repositories.IStockRepository;
import com.stock.stock.services.IStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements IStockService {

    private final IStockRepository iStockRepository;

    private final IArticlesRepository iArticlesRepository;

    private final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

    public StockServiceImpl(IStockRepository iStockRepository, IArticlesRepository iArticlesRepository) {
        this.iStockRepository = iStockRepository;
        this.iArticlesRepository = iArticlesRepository;
    }


    @Override
    public PaginatedList<StockDTO> getAvailableStock(PageCriteria pageCriteria) {
        Pageable pageable = PageRequest.of(pageCriteria.getPage(), pageCriteria.getSize());
        Page<Stock> stockAvailablePageable = this.iStockRepository.findAll(pageable);
        List<Stock> listStockAvailable = stockAvailablePageable.stream().toList();
        List<StockDTO> listStockAvailableDTO = listStockAvailable.stream()
                .map(StockDTO::new)
                .toList();
        return new PaginatedList<>(listStockAvailableDTO,
                stockAvailablePageable.getTotalElements(),
                Long.parseLong(String.valueOf(pageCriteria.getSize())),
                Long.parseLong(String.valueOf(pageCriteria.getPage()))
        );
    }

    @Override
    public StockDTO getStockByCodeArticle(Integer codeArticle) {
        Article article = this.iArticlesRepository.findArticleByArticleCode(codeArticle);
        if (article == null) {
            return null;
        }

        Stock stock = this.iStockRepository.getStockByArticle(article);

        return new StockDTO(stock);
    }

    @Override
    public Boolean createStock(StockDTO stockDTO) {
        Article article = this.iArticlesRepository.findArticleByArticleCode(stockDTO.getArticleCode());
        if (article == null) {
            log.error("El articulo " + stockDTO.getArticleName() + " no existe");
            return false;
        }

        Stock stock = new Stock(stockDTO.getQuantityAvailable(),
                stockDTO.getQuantityPendingEntry(),
                article);

        iStockRepository.save(stock);
        log.info("Stock creado con exito");
        return true;
    }

    @Override
    public Boolean editStock(StockDTO stockDTO) {
        Article article = this.iArticlesRepository.findArticleByArticleCode(stockDTO.getArticleCode());
        if (article == null) {
            log.error("El articulo " + stockDTO.getArticleName() + " no existe");
            return false;
        }

        Stock stock = this.iStockRepository.getStockByArticle(article);
        this.iStockRepository.save(stock);
        return null;
    }
}
