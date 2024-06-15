package edu.upc.dsa.ExceptionMapper;

public class WrongCredentialsException extends Throwable{

    private static final String errorMessage = "El usuario o la contraseña no es correcta";

    public WrongCredentialsException() {
        super(errorMessage);
    }
}
