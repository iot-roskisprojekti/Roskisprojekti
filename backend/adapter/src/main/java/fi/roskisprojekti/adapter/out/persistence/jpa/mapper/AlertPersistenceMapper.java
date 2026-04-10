package fi.roskisprojekti.adapter.out.persistence.jpa.mapper;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.AlertJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.entity.BinJpaEntity;
import fi.roskisprojekti.domain.common.EndTimeStamp;
import fi.roskisprojekti.domain.common.StartTimeStamp;
import fi.roskisprojekti.domain.entity.alert.Alert;
import fi.roskisprojekti.domain.entity.alert.AlertId;
import fi.roskisprojekti.domain.entity.bin.BinId;
import org.springframework.stereotype.Component;

@Component
public class AlertPersistenceMapper {

    public Alert toDomainEntity(AlertJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        EndTimeStamp closedAt = (jpaEntity.getClosedAt() != null)
                ? new EndTimeStamp(jpaEntity.getClosedAt())
                : null;

        return new Alert(
                new AlertId(jpaEntity.getId()),
                new BinId(jpaEntity.getBinJpaEntity().getId()),
                jpaEntity.getAlertState(),
                jpaEntity.getAlertType(),
                new StartTimeStamp(jpaEntity.getCreatedAt()),
                closedAt
        );
    }

    public AlertJpaEntity toJpaEntity(Alert alert, BinJpaEntity binJpaEntity) {
        if (alert == null) return null;

        AlertJpaEntity jpaEntity = new AlertJpaEntity();

        if (alert.getAlertId() != null) {
            jpaEntity.setId(alert.getAlertId().value());
        }

        jpaEntity.setBinJpaEntity(binJpaEntity);
        jpaEntity.setAlertState(alert.getAlertState());
        jpaEntity.setAlertType(alert.getAlertType());

        jpaEntity.setCreatedAt(alert.getTriggeredAt() != null ? alert.getTriggeredAt().value() : null);
        jpaEntity.setClosedAt(alert.getClosedAt() != null ? alert.getClosedAt().value() : null);

        return jpaEntity;
    }
}