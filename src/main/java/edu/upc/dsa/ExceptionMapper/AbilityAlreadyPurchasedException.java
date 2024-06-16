package edu.upc.dsa.ExceptionMapper;

public class AbilityAlreadyPurchasedException extends Exception {
    private static final String errorMessage = "El usuario ya tiene esta habilidad comprada";

    public AbilityAlreadyPurchasedException() {
        super(errorMessage);
    }
}
