package fi.roskisprojekti.adapter.out.persistence.jpa.mapper;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.MeasurementJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.BinJpaRepository;
import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;
import fi.roskisprojekti.domain.entity.telemetry.bin.Distance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BinTelemetryPersistenceMapper {

    private final BinJpaRepository binJpaRepository;


    public MeasurementJpaEntity toJpaEntity(BinTelemetry binTelemetry) {
        if (binTelemetry == null) return null;

        MeasurementJpaEntity entity = new MeasurementJpaEntity();

        entity.setBinJpaEntity(binJpaRepository.getReferenceById(binTelemetry.getBinId().value()));

        int distanceInt = (int) Math.round(binTelemetry.getDistance().value());
        entity.setDistanceMm(distanceInt);
        entity.setMeasuredAt(binTelemetry.getMeasuredAt().value());

        return entity;
    }


    public BinTelemetry toDomainEntity(MeasurementJpaEntity entity) {
        if (entity == null) return null;

        return new BinTelemetry(
                new BinId(entity.getBinJpaEntity().getId()),
                new Distance(entity.getDistanceMm()),
                new SingleTimeStamp(entity.getMeasuredAt())
        );
    }
}