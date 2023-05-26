package org.serratec.sales_manager_grupo5.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.serratec.sales_manager_grupo5.exception.EntidadeExistenteException;
import org.serratec.sales_manager_grupo5.exception.EntidadeNaoEncontradaException;
import org.serratec.sales_manager_grupo5.exception.ErroResposta;
import org.serratec.sales_manager_grupo5.exception.UnauthorizedUserException;
import org.serratec.sales_manager_grupo5.exception.UnmatchingPasswordException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeExistenteException.class)
    public ResponseEntity<ErroResposta> handleResourceBadRequestException(EntidadeExistenteException ex) {
        List<String> erros = new ArrayList<>();
        erros.add(ex.getMessage());
        ErroResposta erro = new ErroResposta(LocalDateTime.now().toString(), 400, "Bad Request",
                erros);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroResposta> handleResourceNotFoundException(EntidadeNaoEncontradaException ex) {
        List<String> erros = new ArrayList<>();
        erros.add(ex.getMessage());
        ErroResposta erro = new ErroResposta(LocalDateTime.now().toString(), 404, "Not Found",
                erros);
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<ErroResposta> handleUnauthorizedException(UnauthorizedUserException ex) {
        List<String> erros = new ArrayList<>();
        erros.add(ex.getMessage());
        ErroResposta erro = new ErroResposta(LocalDateTime.now().toString(), 401, "Unauthorized",
                erros);
        return new ResponseEntity<>(erro, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnmatchingPasswordException.class)
    public ResponseEntity<ErroResposta> handleUnmatchingPasswordException(UnmatchingPasswordException ex) {
        List<String> erros = new ArrayList<>();
        erros.add(ex.getMessage());
        ErroResposta erro = new ErroResposta(LocalDateTime.now().toString(), 400, "Bad Request",
                erros);
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    // -----------------------------------------------------------------------------------------

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> erros = new ArrayList<>();
        erros.add(ex.getMessage());
        ErroResposta erroResposta = new ErroResposta(LocalDateTime.now().toString(), status.value(),
                "Método não suportado", erros);
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> erros = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors())
            erros.add(error.getField() + ": " + error.getDefaultMessage());
        ErroResposta erroResposta = new ErroResposta(LocalDateTime.now().toString(), status.value(),
                "Existem campos inválidos", erros);
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        List<String> erros = new ArrayList<>();
        erros.add(ex.getMessage());
        ErroResposta erroResposta = new ErroResposta(LocalDateTime.now().toString(), status.value(),
                "Not Found", erros);
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

}
