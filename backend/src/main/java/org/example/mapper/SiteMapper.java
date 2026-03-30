package org.example.mapper;

import org.example.model.dto.SiteDto;
import org.example.model.entity.MeasurementEntity;
import org.example.model.entity.SiteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface SiteMapper {

    @Mapping(target = "id", source = "site.id")
    @Mapping(target = "fillPercent", expression = "java(latest != null ? latest.getFillPercent().intValue() : 0)")
    @Mapping(target = "status", expression = "java(determineStatus(latest))")
    @Mapping(target = "lastUpdated", expression = "java(latest != null ? latest.getMeasuredAt() : java.time.LocalDateTime.now())")
    @Mapping(target = "capacity", source = "site.capacityLiters")

    SiteDto toDto(SiteEntity site, MeasurementEntity latest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "capacityLiters", source = "capacity")
    void updateEntityFromDto(SiteDto dto, @MappingTarget SiteEntity site);

    default String determineStatus(MeasurementEntity latest) {
        if (latest == null) return "OK";
        double fill = latest.getFillPercent().doubleValue();
        if (fill >= 90.0) return "KRIITTINEN";
        if (fill >= 70.0) return "VAROITUS";
        return "OK";
    }
}