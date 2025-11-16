package com.projetofinal.ged.infra.exceptions;

public class FileIsEmpty extends RuntimeException {
    public FileIsEmpty() {
        super("Não foi enviado nenhum arquivo");
    }
}
