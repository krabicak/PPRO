package cz.uhk.ppro.vehicle.registry.common.exceptions;

public class FaultLoginException extends Exception {

    public FaultLoginException() {
    }

    public FaultLoginException(String message) {
        super(message);
    }

    public FaultLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public FaultLoginException(Throwable cause) {
        super(cause);
    }

    public FaultLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
