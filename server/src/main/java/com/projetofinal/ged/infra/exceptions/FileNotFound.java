package com.projetofinal.ged.infra.exceptions;

public class FileNotFound extends RuntimeException {
    public FileNotFound() {
        super("Arquivo não encontrado");
    }
}
