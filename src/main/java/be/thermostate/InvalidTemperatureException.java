package be.thermostate;

public class InvalidTemperatureException extends RuntimeException {
    public InvalidTemperatureException(String message) {
        super(message);
    }
}
