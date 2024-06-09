package com.stock.stock.exception.handler;

import com.stock.stock.dto.CustomErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomErrorResponseDTO handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fe -> {
            errors.put(fe.getField(), fe.getDefaultMessage());
        });
        return new CustomErrorResponseDTO(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                errors,
                new Date().toString()
        );
    }

    private String dateToString(Date dateToConvert) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy'T'hh:mm:ss");
        return sdf.format(dateToConvert);
    }
}
