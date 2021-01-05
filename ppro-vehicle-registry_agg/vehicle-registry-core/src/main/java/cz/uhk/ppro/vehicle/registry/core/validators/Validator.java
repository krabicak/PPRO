package cz.uhk.ppro.vehicle.registry.core.validators;

public abstract class Validator {

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
