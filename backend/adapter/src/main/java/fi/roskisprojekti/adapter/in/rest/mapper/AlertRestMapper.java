package fi.roskisprojekti.adapter.in.rest.mapper;

import fi.roskisprojekti.adapter.in.rest.dto.AlertRestDto;
import fi.roskisprojekti.domain.entity.alert.Alert;
import org.springframework.stereotype.Component;

@Component
public class AlertRestMapper {

    public AlertRestDto toRestDto(Alert alert) {
        if (alert == null) return null;

        return new AlertRestDto(
                alert.getAlertId() != null ? alert.getAlertId().value() : null,
                alert.getBinId() != null ? alert.getBinId().value() : null, //
                alert.getAlertType() != null ? alert.getAlertType().name() : null,
                alert.getAlertState() != null ? alert.getAlertState().name() : null,
                alert.getTriggeredAt() != null ? alert.getTriggeredAt().value() : null,
                alert.getClosedAt() != null ? alert.getClosedAt().value() : null

        );
    }
}