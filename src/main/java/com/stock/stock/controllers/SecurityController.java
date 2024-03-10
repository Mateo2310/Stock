package com.stock.stock.controllers;

import com.stock.stock.dto.StockDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class SecurityController {

    @GetMapping("/find")
    public ResponseEntity<?> findTest() {
        List<StockDTO> sotcks = Collections.singletonList(new StockDTO(
                new BigDecimal(1),
                new BigDecimal(2),
                "nuvo articulo"
        ));

        return new ResponseEntity<>(sotcks, HttpStatus.OK);
    }

    @GetMapping("/authorized")
    public Map<String, String> authorized(@RequestParam String code) {
        return Collections.singletonMap("code", code);
    }
}
