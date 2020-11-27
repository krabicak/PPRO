package cz.uhk.ppro.vehicle.registry.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, Integer> {


    public Integer convertToDatabaseColumn(Boolean value) {
        return Boolean.TRUE.equals(value) ? 1 : 0;
    }

    public Boolean convertToEntityAttribute(Integer value) {
        return value.equals(1);
    }
}
