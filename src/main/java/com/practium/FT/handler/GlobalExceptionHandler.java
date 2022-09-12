package com.practium.FT.handler;

import com.practium.FT.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(NotFoundException.class)
   public ResponseEntity<?> notFoundException(NotFoundException notFoundException) {
       return new ResponseEntity<>(new NotFoundException(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
   }


}