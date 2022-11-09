package com.group03.desafio_integrador.advice;

import com.group03.desafio_integrador.advice.exeptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidationErrorDetail> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ValidationErrorDetail.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .rejectedValue(String.valueOf(fieldError.getRejectedValue()))
                        .build())
                .collect(Collectors.toList());

        var errorDetail = ExceptionDetails.builder()
                .title("Validation Error")
                .message("The following validation(s) error(s) occurred:")
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .timeStamp(LocalDateTime.now())
                .errors(errors)
                .build();

        return new ResponseEntity<>(errorDetail, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Not found!")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .errors(ex.getErrors())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
}
