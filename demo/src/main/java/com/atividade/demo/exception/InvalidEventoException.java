package com.atividade.demo.exception;

public class InvalidEventoException extends RuntimeException {
    public InvalidEventoException (String message) {
        super(message);
    }

    public InvalidEventoException () {
        super("Evento procurado nao encontrado.");
    }
}
