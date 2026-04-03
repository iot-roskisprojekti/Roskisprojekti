package fi.roskisprojekti.adapter.out.persistence.jpa.converter;

import fi.roskisprojekti.domain.entity.alert.AlertType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AlertTypeConverter implements AttributeConverter<AlertType, String> {

    @Override
    public String convertToDatabaseColumn(AlertType type) {
        if (type == null) return null;
        return switch (type) {
            case WARNING -> "VAROITUS";
            case ALERT -> "HÄLYTYS";
        };
    }

    @Override
    public AlertType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "VAROITUS" -> AlertType.WARNING;
            case "HÄLYTYS" -> AlertType.ALERT;
            default -> throw new IllegalArgumentException("Unknown DB AlertType: " + dbData);
        };
    }
}