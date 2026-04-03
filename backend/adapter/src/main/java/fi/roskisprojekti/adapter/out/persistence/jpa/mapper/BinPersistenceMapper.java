package fi.roskisprojekti.adapter.out.persistence.jpa.mapper;


import fi.roskisprojekti.adapter.out.persistence.jpa.entity.BinJpaEntity;
import fi.roskisprojekti.domain.entity.bin.*;
import fi.roskisprojekti.domain.entity.site.SiteId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BinPersistenceMapper {

    @Mapping(target = "binId", source = "id")
    @Mapping(target = "siteId", source = "siteJpaEntity.id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "capacity", source = "capacityLiters")
    @Mapping(target = "dimensions", source = "heightMm")
    @Mapping(target = "fillLevel", source = "fillLevelPercent")
    @Mapping(target = "alertThreshold", expression = "java(new AlertThreshold(70, 90))")
    @Mapping(target = "lastUpdated", source = "lastMeasuredAt")
    Bin toDomainEntity(BinJpaEntity entity);

    @Mapping(target = "id", source = "binId.value")
    @Mapping(target = "siteJpaEntity.id", source = "siteId.value")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "capacityLiters", source = "capacity")
    @Mapping(target = "heightMm", source = "dimensions.height")
    @Mapping(target = "fillLevelPercent", source = "fillLevel.percent")
    @Mapping(target = "lastMeasuredAt", source = "lastUpdated.value")
    BinJpaEntity toJpaEntity(Bin bin);


    default Dimensions mapDimensions(Integer height) {
        return new Dimensions(height != null ? height : 1000, 1, 2);
    }

    default BinId mapBinId(Long id) { return id != null ? new BinId(id) : null; }
    default SiteId mapSiteId(Long id) { return id != null ? new SiteId(id) : null; }
    default FillLevel mapFillLevel(java.math.BigDecimal percent) {
        return percent != null ? new FillLevel(percent.doubleValue()) : new FillLevel(0.0);
    }
    default LastUpdated mapLastUpdated(java.time.Instant time) {

        if (time == null) {
            return new LastUpdated(java.time.Instant.EPOCH);
        }
        return new LastUpdated(time);
    }
}