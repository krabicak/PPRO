package cz.uhk.ppro.vehicle.registry.common.exceptions;

public class VinException extends Exception{
    public VinException() {
    }

    public VinException(String message) {
        super(message);
    }

    public VinException(String message, Throwable cause) {
        super(message, cause);
    }

    public VinException(Throwable cause) {
        super(cause);
    }

    public VinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
