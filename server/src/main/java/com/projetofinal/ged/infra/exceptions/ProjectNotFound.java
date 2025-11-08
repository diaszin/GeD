package com.projetofinal.ged.infra.exceptions;

public class ProjectNotFound extends RuntimeException {
  public ProjectNotFound() {
    super("Projeto não encontrado");
  }
}
