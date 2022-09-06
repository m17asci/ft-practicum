package com.practium.FT.handler;

import com.practium.FT.exception.CommentNotFoundException;
import com.practium.FT.exception.ProductNotFoundException;
import com.practium.FT.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> productNotFound(ProductNotFoundException notFoundException) {
        return new ResponseEntity<>(new ProductNotFoundException(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException notFoundException) {
        return new ResponseEntity<>(new UserNotFoundException(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> commentNotFoundException(CommentNotFoundException notFoundException) {
        return new ResponseEntity<>(new CommentNotFoundException(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }


}