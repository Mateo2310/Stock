package com.stock.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CustomErrorResponseDTO {
    private int errorCode;
    private Map<String, String> message;
    private String timestamp;

    public CustomErrorResponseDTO(int errorCode, Map<String, String> message, String timestamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = timestamp;
    }
}
