package fi.roskisprojekti.adapter.out.persistence.jpa.converter;

import fi.roskisprojekti.domain.entity.alert.AlertState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AlertStateConverter implements AttributeConverter<AlertState, String> {
    @Override
    public String convertToDatabaseColumn(AlertState attribute) {
        if (attribute == null) return null;
        return switch (attribute) {
            case OPEN -> "AVOIN";
            case ACKNOWLEDGED -> "KUITATTU";
            case CLOSED -> "SULJETTU";
        };
    }

    @Override
    public AlertState convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "AVOIN" -> AlertState.OPEN;
            case "KUITATTU" -> AlertState.ACKNOWLEDGED;
            case "SULJETTU" -> AlertState.CLOSED;
            default -> throw new IllegalArgumentException("Unknown DB state: " + dbData);
        };
    }
}