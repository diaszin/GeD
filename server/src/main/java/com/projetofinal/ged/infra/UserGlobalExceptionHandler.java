package com.projetofinal.ged.infra;

import com.projetofinal.ged.infra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserGlobalExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<DefaultExceptionResponse> userNotFound(UserNotFound error){
        DefaultExceptionResponse response = new DefaultExceptionResponse(error.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserPasswordNotMatch.class)
    public ResponseEntity<DefaultExceptionResponse> passwordUserNotMatch(UserPasswordNotMatch error){
        DefaultExceptionResponse response = new DefaultExceptionResponse(error.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserEmailExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultExceptionResponse userEmailExists(UserEmailExists error){
        return new DefaultExceptionResponse(error.getMessage());
    }
}
