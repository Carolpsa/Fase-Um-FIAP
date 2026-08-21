package br.com.fiap.faseUm.FaseUm.controllers.handlers;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fiap.faseUm.FaseUm.services.exceptions.ResourceAlreadyExistsException;
import br.com.fiap.faseUm.FaseUm.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handlerResourceNotFoundException(ResourceNotFoundException e) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    pd.setTitle("Resource not found");
    pd.setType(URI.create("https://faseum.fiap.com.br/errors/resource-not-found"));
    return pd;
}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    pd.setTitle("Validation error");
    Map<String, String> errors = new LinkedHashMap<>();
    for (var err : e.getBindingResult().getFieldErrors()) {
        errors.put(err.getField(), err.getDefaultMessage());
    }
    pd.setProperty("errors", errors); 
    return pd;
}

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ProblemDetail handlerResourceAlreadyExistsException(ResourceAlreadyExistsException e, HttpServletRequest request) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    pd.setTitle("Recurso já existe");
    pd.setType(URI.create("https://faseum.fiap.com.br/erros/recurso-duplicado"));
    pd.setInstance(URI.create(request.getRequestURI()));
    return pd;
    }
}
