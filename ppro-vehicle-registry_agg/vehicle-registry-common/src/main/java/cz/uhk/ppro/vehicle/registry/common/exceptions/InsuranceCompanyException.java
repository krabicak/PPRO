package cz.uhk.ppro.vehicle.registry.common.exceptions;

public class InsuranceCompanyException extends Exception{
    public InsuranceCompanyException() {
    }

    public InsuranceCompanyException(String message) {
        super(message);
    }

    public InsuranceCompanyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsuranceCompanyException(Throwable cause) {
        super(cause);
    }

    public InsuranceCompanyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
