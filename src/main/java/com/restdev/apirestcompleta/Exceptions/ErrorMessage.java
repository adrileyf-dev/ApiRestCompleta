package com.restdev.apirestcompleta.Exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private String path;
    private String method;
    private int status;
    private String statusText;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String,String> erros;
    public ErrorMessage(HttpServletRequest request, HttpStatus status,String msg){
        this.path       = request.getRequestURI();
        this.method     = request.getMethod();
        this.status     = status.value();
        this.statusText = status.getReasonPhrase();
        this.message    = msg;
    }
    public  ErrorMessage(HttpServletRequest request, HttpStatus status, String msg, BindingResult result){
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status =  status.value();
        this.statusText = status.getReasonPhrase();
        this.message = msg;
        addErros(result);
    }
    private void addErros(BindingResult result) {
        this.erros = new HashMap<>();
        for (FieldError fieldError: result.getFieldErrors()){
            this.erros.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
    }
}
