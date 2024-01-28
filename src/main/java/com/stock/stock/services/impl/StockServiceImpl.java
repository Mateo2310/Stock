package com.stock.stock.services.impl;

import com.stock.stock.dto.PageCriteria;
import com.stock.stock.dto.PaginatedList;
import com.stock.stock.dto.StockDTO;
import com.stock.stock.entities.Stock;
import com.stock.stock.repositories.IStockRepository;
import com.stock.stock.services.IStockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements IStockService {

    private final IStockRepository iStockRepository;

    public StockServiceImpl(IStockRepository iStockRepository) {
        this.iStockRepository = iStockRepository;
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
    public PaginatedList<StockDTO> getAvailableStockByArticle(String articleName) {

        return null;
    }
}
