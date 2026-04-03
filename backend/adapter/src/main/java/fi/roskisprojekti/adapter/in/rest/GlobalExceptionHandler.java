package fi.roskisprojekti.adapter.in.rest;

import fi.roskisprojekti.domain.validation.DomainValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<Map<String, String>> handleDomainValidation(DomainValidationException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "virhe_erhe_sos_DOMAIN_error", ex.getMessage()
        ));
    }
}