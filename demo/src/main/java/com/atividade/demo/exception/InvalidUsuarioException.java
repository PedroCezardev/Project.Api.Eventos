package com.atividade.demo.exception;

public class InvalidUsuarioException extends RuntimeException {
    public InvalidUsuarioException (String message) {
        super(message);
    }

    public InvalidUsuarioException () {
        super("Usuario procurado nao encontrado.");
    }
}
