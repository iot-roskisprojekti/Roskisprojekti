package fi.roskisprojekti.adapter.out.persistence.jpa.converter;

import fi.roskisprojekti.domain.entity.message.MessageStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MessageStatusConverter implements AttributeConverter<MessageStatus, String> {

    @Override
    public String convertToDatabaseColumn(MessageStatus status) {
        if (status == null) return null;
        return switch (status) {
            case SENT -> "LÄHETETTY";
            case FAILED -> "EPÄONNISTUNUT";
        };
    }

    @Override
    public MessageStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "LÄHETETTY" -> MessageStatus.SENT;
            case "EPÄONNISTUNUT" -> MessageStatus.FAILED;
            default -> throw new IllegalArgumentException("Unknown MessageStatus in DB: " + dbData);
        };
    }
}