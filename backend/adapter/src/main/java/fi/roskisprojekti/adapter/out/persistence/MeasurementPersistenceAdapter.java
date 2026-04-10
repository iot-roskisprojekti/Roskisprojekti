package fi.roskisprojekti.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import fi.roskisprojekti.adapter.out.persistence.jpa.entity.MeasurementJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.mapper.MeasurementPersistenceMapper;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.MeasurementJpaRepository;
import fi.roskisprojekti.application.port.out.persistence.MeasurementRepository;
import fi.roskisprojekti.domain.entity.measurement.Measurement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MeasurementPersistenceAdapter implements MeasurementRepository {

    private final MeasurementJpaRepository jpaRepository;
    private final MeasurementPersistenceMapper mapper;

    @Override
    public void save(Measurement measurement) {
        MeasurementJpaEntity entity = mapper.toJpaEntity(measurement);

        jpaRepository.save(entity);
    }

    @Override
    public List<Measurement> findAllMeasurements() {
        List<MeasurementJpaEntity> entities = jpaRepository.findAll();

        return entities.stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Measurement> findByBinIdOrderByTimestampAsc(Long binId) {
        return jpaRepository.findByBinJpaEntity_IdOrderByMeasuredAtAsc(binId)
                .stream()
                .map(mapper::toDomainEntity)
                .toList();
}

}