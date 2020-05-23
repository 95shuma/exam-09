package com.exam9.forum.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@ControllerAdvice(annotations = RestController.class)
public class BindExceptionHandler {
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Object> handleBindException(BindException ex){
        var br = ex.getBindingResult();
        var apiFieldErrors = br.getFieldErrors()
                .stream()
                .map(f->String.format("%s - %s",f.getField(),f.getDefaultMessage()))
                .collect(toList());
        return ResponseEntity.unprocessableEntity()
                .body(apiFieldErrors);
    }
}
