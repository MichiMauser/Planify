package com.example.planify.exceptions;

import com.example.planify.exceptions.APIExceptionResponse;
import jakarta.validation.ValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.NoSuchElementException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    //Metoda de handling specifica pentru tratarea exceptiei definite de noi (ApiExceptionResponse)
    //Daca mai adaugam alte exceptii, mai punem o @ExceptionHandle(value = "nume clasa de exceptii creata de noi".class)
    @ExceptionHandler(value = APIExceptionResponse.class)
    protected ResponseEntity<Object> handleApiExceptionResponse(APIExceptionResponse ex) {
        HttpStatus status = ex.getStatus() != null ? ex.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
        return responseEntityBuilder(APIExceptionResponse.builder()
                .errors(ex.getErrors())
                .status(status)
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementResponse(NoSuchElementException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return responseEntityBuilder(APIExceptionResponse.builder()
                .status(status)
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ex) {
        HttpStatus status = HttpStatus.OK;
        return responseEntityBuilder(APIExceptionResponse.builder()
                .status(status)
                .message(ex.getMessage())
                .build()
        );
    }

    private ResponseEntity<Object> responseEntityBuilder(APIExceptionResponse ex) {
        return new ResponseEntity<>(ex, ex.getStatus());
    }
}