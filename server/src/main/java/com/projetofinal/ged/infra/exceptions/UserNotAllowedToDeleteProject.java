package com.projetofinal.ged.infra.exceptions;

public class UserNotAllowedToDeleteProject extends RuntimeException {
  public UserNotAllowedToDeleteProject() {
    super("Usuário não tem permissão para deletar o projeto");
  }
}
