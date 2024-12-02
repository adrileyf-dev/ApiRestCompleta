package com.restdev.apirestcompleta.Exceptions;

import com.restdev.apirestcompleta.exception.NewPasswordMismatchException;
import com.restdev.apirestcompleta.exception.UsernameUniqueViolationException;
import com.restdev.apirestcompleta.exception.entityNotFoudExcept;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result){
     log.error("Api error", ex);
     return  ResponseEntity
             .status(HttpStatus.UNPROCESSABLE_ENTITY)
             .contentType(MediaType.APPLICATION_JSON)
             .body(new ErrorMessage(request,HttpStatus.UNPROCESSABLE_ENTITY,"Campos(S) invalidos(S)",result));
    }

    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(RuntimeException ex,
                                                                        HttpServletRequest request){
     log.error("Api error", ex);
     return  ResponseEntity
             .status(HttpStatus.CONFLICT)
             .contentType(MediaType.APPLICATION_JSON)
             .body(new ErrorMessage(request,HttpStatus.CONFLICT,ex.getMessage()));
    }
    @ExceptionHandler(entityNotFoudExcept.class)
    public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException ex,
                                                                HttpServletRequest request){
     log.error("Api error", ex);
     return  ResponseEntity
             .status(HttpStatus.NOT_FOUND)
             .contentType(MediaType.APPLICATION_JSON)
             .body(new ErrorMessage(request,HttpStatus.NOT_FOUND,ex.getMessage()));
    }
    @ExceptionHandler(NewPasswordMismatchException.class)
    public ResponseEntity<ErrorMessage> newPasswordMismatchException(RuntimeException ex,
                                                                HttpServletRequest request){
     log.error("Api error", ex);
     return  ResponseEntity
             .status(HttpStatus.BAD_REQUEST)
             .contentType(MediaType.APPLICATION_JSON)
             .body(new ErrorMessage(request,HttpStatus.BAD_REQUEST,ex.getMessage()));
    }
}
