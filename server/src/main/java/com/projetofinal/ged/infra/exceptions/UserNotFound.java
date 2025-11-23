package com.projetofinal.ged.infra.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("Usuário não encontrado");
    }
}
