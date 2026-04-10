package fi.roskisprojekti.adapter.out.persistence.jpa.mapper;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.BinJpaEntity;
import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.entity.bin.*;
import fi.roskisprojekti.domain.entity.site.SiteId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.time.Instant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BinPersistenceMapper {

    @Mapping(target = "binId", source = "id")
    @Mapping(target = "siteId", source = "siteJpaEntity.id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "capacity", source = "capacityLiters")
    @Mapping(target = "dimensions", source = "heightMm")
    @Mapping(target = "fillLevel", source = "fillLevelPercent")
    @Mapping(target = "fillAlertThreshold", source = ".")
    @Mapping(target = "staleThresholdMinutes", source = ".")
    @Mapping(target = "lastUpdated", source = "lastMeasuredAt")
    Bin toDomainEntity(BinJpaEntity entity);

    @Mapping(target = "id", source = "binId.value")
    @Mapping(target = "siteJpaEntity.id", source = "siteId.value")
    @Mapping(target = "name", source = "name.value")
    @Mapping(target = "capacityLiters", source = "capacity.value")
    @Mapping(target = "heightMm", source = "dimensions.height")
    @Mapping(target = "fillLevelPercent", source = "fillLevel.percent")
    @Mapping(target = "lastMeasuredAt", source = "lastUpdated.value")
    BinJpaEntity toJpaEntity(Bin bin);



    default Dimensions mapDimensions(Integer height) {
        return new Dimensions(height != null ? height.doubleValue() : 1000.0, 1.0, 1.0);
    }

    default Capacity mapCapacity(BigDecimal value) {
        return value != null ? new Capacity(value.doubleValue()) : null;
    }

    default FillLevel mapFillLevel(BigDecimal value) {
        return value != null ? new FillLevel(value.doubleValue()) : new FillLevel(0.0);
    }


    default Integer mapHeight(double value) {
        return (int) value;
    }

    default BigDecimal mapToBigDecimal(double value) {
        return BigDecimal.valueOf(value);
    }


    default BinId mapBinId(Long id) { return id != null ? new BinId(id) : null; }

    default SiteId mapSiteId(Long id) { return id != null ? new SiteId(id) : null; }

    default Name mapName(String v) { return v != null ? new Name(v) : null; }

    default StaleThresholdMinutes mapStaleThreshold(BinJpaEntity entity) {
        return new StaleThresholdMinutes(60);
    }

    default FillAlertThreshold mapAlertThreshold(BinJpaEntity entity) {
        return new FillAlertThreshold(70, 90);
    }
    default SingleTimeStamp mapLastUpdated(Instant time) {
        return new SingleTimeStamp(time != null ? time : Instant.EPOCH);
    }
}