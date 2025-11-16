package com.projetofinal.ged.infra;

import com.projetofinal.ged.application.FileController;
import com.projetofinal.ged.infra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = FileController.class)
public class FileGlobalExceptionHandler {
    @ExceptionHandler(FileIsEmpty.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultExceptionResponse fileIsEmpty(FileIsEmpty e) {
        return new DefaultExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(FileNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultExceptionResponse fileNotFound(FileNotFound e) {
        return new DefaultExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(FileNotCreated.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultExceptionResponse fileNotCreated(FileNotCreated e) {
        return new DefaultExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(FileNotRemoved.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultExceptionResponse fileNotRemoved(FileNotRemoved e) {
        return new DefaultExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DefaultExceptionResponse internalServerError(Exception error) {
        return new DefaultExceptionResponse("Houve um erro desconhecido !");
    }

    @ExceptionHandler(NotSupportedExtensionFile.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultExceptionResponse notSupportedFileExtension(NotSupportedExtensionFile error) {
        return new DefaultExceptionResponse(error.getMessage());
    }
}
