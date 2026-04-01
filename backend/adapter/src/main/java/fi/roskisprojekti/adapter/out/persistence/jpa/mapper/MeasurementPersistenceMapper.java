package fi.roskisprojekti.adapter.out.persistence.jpa.mapper;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.BinJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.entity.MeasurementJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.BinJpaRepository;
import fi.roskisprojekti.domain.bin.BinId;
import fi.roskisprojekti.domain.measurement.Distance;
import fi.roskisprojekti.domain.measurement.MeasuredAt;
import fi.roskisprojekti.domain.measurement.Measurement;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class MeasurementPersistenceMapper {

    @Autowired // Spring injects this into the generated subclass
    protected BinJpaRepository binJpaRepository;

    // --- Domain -> JPA ---

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "binJpaEntity", source = "binId", qualifiedByName = "toBinProxy")
    @Mapping(target = "distanceMm", source = "distance.value")
    @Mapping(target = "measuredAt", source = "measuredAt.value")
    public abstract MeasurementJpaEntity toJpaEntity(Measurement domain);

    // --- JPA -> Domain ---

    @Mapping(target = "binId", source = "binJpaEntity.id")
    @Mapping(target = "distance", source = "distanceMm")
    @Mapping(target = "measuredAt", source = "measuredAt")
    public abstract Measurement toDomainEntity(MeasurementJpaEntity entity);

    // --- Helper Methods ---

    @Named("toBinProxy")
    protected BinJpaEntity toBinProxy(BinId binId) {
        if (binId == null) return null;
        // Accesses the injected repository instance
        return binJpaRepository.getReferenceById(binId.value());
    }

    protected BinId mapToBinId(Long value) {
        return value != null ? new BinId(value) : null;
    }

    protected Distance mapToDistance(Integer value) {
        return value != null ? new Distance(value) : null;
    }

    protected MeasuredAt mapToMeasuredAt(Instant value) {
        return value != null ? new MeasuredAt(value) : null;
    }
}