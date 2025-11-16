package com.projetofinal.ged.infra.exceptions;

public class FileNotCreated extends RuntimeException {
    public FileNotCreated() {
        super("O arquivo não foi criado !");
    }
}
