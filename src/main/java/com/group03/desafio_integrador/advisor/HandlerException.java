package com.group03.desafio_integrador.advisor;

import com.group03.desafio_integrador.advisor.exceptions.NotAcceptableException;
import com.group03.desafio_integrador.advisor.exceptions.NotFoundException;
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

    /**
     * Método responsável pelo tratamento das exceções geradas ao validar os campos de uma entidade.
     * @author Ingrid Paulino
     * @param ex - MethodArgumentNotValidException
     * @param headers - HttpHeaders
     * @param status - HttpStatus
     * @param request - WebRequest
     * @return exceptionDetails - Retorna uma entidade do tipo ExceptionDetails.
     */
    @Override
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

    /**
     * Método responsável pelo tratamento das exceções geradas quando a solicitação não for encontrada.
     * @author Ingrid Paulino
     * @param ex - NotFoundException
     * @return exceptionDetails - Retorna uma entidade do tipo ExceptionDetails.
     */
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

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<ExceptionDetails> handlerNotAcceptableException(NotAcceptableException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Not Exception!")
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_ACCEPTABLE);
    }
}
