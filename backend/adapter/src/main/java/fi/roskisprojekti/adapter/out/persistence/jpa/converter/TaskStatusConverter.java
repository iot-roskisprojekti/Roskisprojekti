package fi.roskisprojekti.adapter.out.persistence.jpa.converter;

import fi.roskisprojekti.domain.task.TaskStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TaskStatusConverter implements AttributeConverter<TaskStatus, String> {
    @Override
    public String convertToDatabaseColumn(TaskStatus status) {
        if (status == null) return null;
        return switch (status) {
            case OPEN -> "AVOIN";
            case IN_PROGRESS -> "KESKEN";
            case COMPLETED -> "VALMIS";
        };
    }

    @Override
    public TaskStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "AVOIN" -> TaskStatus.OPEN;
            case "KESKEN" -> TaskStatus.IN_PROGRESS;
            case "VALMIS" -> TaskStatus.COMPLETED;
            default -> throw new IllegalArgumentException("Unknown Task status: " + dbData);
        };
    }
}