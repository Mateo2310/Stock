package com.stock.stock.controllers;

import com.stock.stock.dto.PageCriteria;
import com.stock.stock.services.IStockService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final Logger log = LoggerFactory.getLogger(StockController.class);

    private final IStockService iStockService;

    public StockController(IStockService iStockService) {
        this.iStockService = iStockService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> getStock(@RequestBody @Valid PageCriteria pageCriteria) {
        log.info("Request to get all stock of articles avaliables");
        try {
            return ResponseEntity.ok(iStockService.getAvailableStock(pageCriteria));
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "500 Internal Server Error");
            response.put("content", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
