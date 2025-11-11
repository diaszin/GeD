package com.projetofinal.ged.infra.exceptions;

public class FolderNotFound extends RuntimeException {
    public FolderNotFound() {
        super("Pasta não encontrada !");
    }
}
