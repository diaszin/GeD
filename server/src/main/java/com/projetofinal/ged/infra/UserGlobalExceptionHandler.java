package com.projetofinal.ged.infra;

import com.projetofinal.ged.infra.exceptions.DefaultExceptionResponse;
import com.projetofinal.ged.infra.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<DefaultExceptionResponse> userNotFound(UserNotFound error){
        DefaultExceptionResponse response = new DefaultExceptionResponse(error.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



}
