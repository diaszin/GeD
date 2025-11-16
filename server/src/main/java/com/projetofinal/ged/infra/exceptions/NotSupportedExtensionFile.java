package com.projetofinal.ged.infra.exceptions;

public class NotSupportedExtensionFile extends RuntimeException {
    public NotSupportedExtensionFile() {
        super("O arquivo enviado não possue a extensão permitida");
    }
}
