package edu.upc.dsa.ExceptionMapper;

public class NotEnoughMoneyException extends Exception {
    private static final String errorMessage = "El usuario no tiene dinero suficiente para comprar la habilidad";

    public NotEnoughMoneyException() {
        super(errorMessage);
    }
}