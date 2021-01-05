package cz.uhk.ppro.vehicle.registry.common.exceptions;

public class SpzException extends Exception{

    public SpzException() {
    }

    public SpzException(String message) {
        super(message);
    }

    public SpzException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpzException(Throwable cause) {
        super(cause);
    }

    public SpzException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
