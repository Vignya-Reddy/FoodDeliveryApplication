package com.fooddelivery.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateItemIDException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateIDException(DuplicateItemIDException e){
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("Duplicate id");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.CONFLICT);
        return responseEntyity;

}
	@ExceptionHandler(InvalidItemIDException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidItemIDException(InvalidItemIDException e){
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.CONFLICT);
        return responseEntyity;
    }
	@ExceptionHandler(DuplicateRestaurantIDException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateRestaurantIDException(DuplicateRestaurantIDException e){
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("Duplicate id");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.CONFLICT);
        return responseEntyity;

}
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
	
	@ExceptionHandler(InvalidRestaurantIDException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRestaurantIDException(InvalidRestaurantIDException e){
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.CONFLICT);
        return responseEntyity;
    }
	
	@ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleItemNotFoundException(ItemNotFoundException e){
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.CONFLICT);
        return responseEntyity;
    }
	
	@ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantNotFoundException(RestaurantNotFoundException e){
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<ExceptionResponse>(response,HttpStatus.CONFLICT);
        return responseEntyity;
    }

}
