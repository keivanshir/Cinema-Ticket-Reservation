 package com.example.cinematicketreservationsystem.exception;

import com.example.cinematicketreservationsystem.dtos.Response;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Response<String>> handleGlobalException(Exception ex, HttpServletRequest request){
        ex.printStackTrace();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        final char RLM = '\u200F'; // Right-to-Left Mark
        final char LRM = '\u200E'; // Left-to-Right Mark

        return new ResponseEntity<>(Response.<String>builder()
                .message("لطفاً با پشتیبانی تماس بگیرید")
                .data(String.format("%s%s %s%s %s%s %s%s", "خطا در مسیر: ", RLM, url, LRM, " در متد: ", RLM, method, LRM))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<Response<String>> handleSeatNotAvailable(SeatNotAvailableException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Response.<String>builder()
                        .message("خطا:")
                        .data(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response<String>> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.<String>builder()
                        .message("خطا:")
                        .data(ex.getMessage())
                        .build());
    }


}
