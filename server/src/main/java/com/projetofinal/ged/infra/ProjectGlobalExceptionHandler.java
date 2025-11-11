package com.projetofinal.ged.infra;

import com.projetofinal.ged.application.ProjectController;
import com.projetofinal.ged.infra.exceptions.DefaultExceptionResponse;
import com.projetofinal.ged.infra.exceptions.ProjectNotFound;
import com.projetofinal.ged.infra.exceptions.UserNotAllowedToDeleteProject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ProjectController.class)
public class ProjectGlobalExceptionHandler {
    @ExceptionHandler(ProjectNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DefaultExceptionResponse projectNotFound(ProjectNotFound exception){
        return new DefaultExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(UserNotAllowedToDeleteProject.class)
    public DefaultExceptionResponse userNotAllowedToDeleteProject(UserNotAllowedToDeleteProject exception){
        return new DefaultExceptionResponse(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DefaultExceptionResponse internalServerError(Exception error) {
        return new DefaultExceptionResponse("Houve um erro desconhecido!");
    }
}
