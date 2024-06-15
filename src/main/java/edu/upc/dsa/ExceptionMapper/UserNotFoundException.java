package edu.upc.dsa.ExceptionMapper;

public class UserNotFoundException extends Throwable {
    private static final String errorMessage = "El usuario no existe";

    public UserNotFoundException() {
        super(errorMessage);
    }
}