package com.projetofinal.ged.infra.exceptions;

public class UserEmailExists extends RuntimeException {
    public UserEmailExists() {
        super("Já existe uma conta com esse email");
    }
}
