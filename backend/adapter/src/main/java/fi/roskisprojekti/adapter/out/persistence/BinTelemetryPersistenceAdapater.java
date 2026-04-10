package fi.roskisprojekti.adapter.out.persistence;

import fi.roskisprojekti.adapter.out.persistence.jpa.mapper.BinTelemetryPersistenceMapper;
import fi.roskisprojekti.application.port.out.persistence.BinTelemetryRepository;
import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;
import lombok.RequiredArgsConstructor;
import fi.roskisprojekti.adapter.out.persistence.jpa.entity.MeasurementJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.MeasurementJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BinTelemetryPersistenceAdapater implements BinTelemetryRepository {

    private final MeasurementJpaRepository measurementJpaRepository;
    private final BinTelemetryPersistenceMapper binTelemetryMapper;

    @Override
    public void save(BinTelemetry measurement) {
        MeasurementJpaEntity entity = binTelemetryMapper.toJpaEntity(measurement);

        measurementJpaRepository.save(entity);
    }

    @Override
    public List<BinTelemetry> findAllBinTelemetry() {
        List<MeasurementJpaEntity> entities = measurementJpaRepository.findAll();

        return entities.stream()
                .map(binTelemetryMapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<BinTelemetry> findAllTelemetryByBinId(BinId binId) {
        List<MeasurementJpaEntity> measurementJpaEntities = measurementJpaRepository.findByBinJpaEntity_Id(binId.value());

        return measurementJpaEntities.stream()
                .map(binTelemetryMapper::toDomainEntity)
                .toList();
    }
}