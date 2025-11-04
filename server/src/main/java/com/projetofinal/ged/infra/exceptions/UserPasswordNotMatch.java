package com.projetofinal.ged.infra.exceptions;

public class UserPasswordNotMatch extends RuntimeException {
    public UserPasswordNotMatch() {
        super("Senha incorreta");
    }
}
