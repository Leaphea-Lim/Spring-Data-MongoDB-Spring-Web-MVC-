package co.istad.itp_mongodb.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestException {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", e.getStatusCode().value());
        errors.put("message", e.getReason());
        errors.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(e.getStatusCode()).body(errors);
    }
}
