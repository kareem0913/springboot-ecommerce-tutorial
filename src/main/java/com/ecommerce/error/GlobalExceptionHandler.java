package com.ecommerce.error;

import com.ecommerce.error.exception.DuplicateResourceException;
import com.ecommerce.error.exception.ResourceNotFoundException;
import com.ecommerce.error.model.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.ecommerce.util.TimeUtil.currentTimestamp;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleNotFoundException(ResourceNotFoundException e) {
        return new ErrorResponse(e.getCode(), e.getMessage(), e.getDescription(), currentTimestamp());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ErrorResponse handleDuplicateResourceException(DuplicateResourceException e) {
        return new ErrorResponse(e.getCode(), e.getMessage(), e.getDescription(), currentTimestamp());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse response = new ErrorResponse(
                400,
                "Validation failed",
                errors,
                currentTimestamp()
                );
        return response;
    }
}
