package com.projetofinal.ged.infra.exceptions;

public class FileNotRemoved extends RuntimeException {
    public FileNotRemoved() {
        super("Não foi possível remover o arquivo");
    }
}
