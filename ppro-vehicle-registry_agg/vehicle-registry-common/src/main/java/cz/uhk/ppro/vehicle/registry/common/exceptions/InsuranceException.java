package cz.uhk.ppro.vehicle.registry.common.exceptions;

public class InsuranceException extends Exception{
    public InsuranceException() {
    }

    public InsuranceException(String message) {
        super(message);
    }

    public InsuranceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsuranceException(Throwable cause) {
        super(cause);
    }

    public InsuranceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
