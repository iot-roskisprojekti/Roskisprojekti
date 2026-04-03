package fi.roskisprojekti.adapter.in.rest.mapper;

import fi.roskisprojekti.adapter.in.rest.dto.BinRestDto;
import fi.roskisprojekti.domain.entity.bin.*;
import fi.roskisprojekti.domain.entity.site.SiteId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface BinRestMapper {

    @Mapping(target = "binId", source = "binId.value")
    @Mapping(target = "siteId", source = "siteId.value")
    @Mapping(target = "fillLevel", source = "fillLevel.percent")
    @Mapping(target = "status", expression = "java(bin.getStatus())")
    @Mapping(target = "lastUpdated", source = "lastUpdated.value")
    BinRestDto toRestDto(Bin bin);

    @Mapping(target = "binId", source = "binId")
    @Mapping(target = "siteId", source = "siteId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "fillLevel", source = "fillLevel")
    @Mapping(target = "lastUpdated", source = "lastUpdated")
    Bin toDomainEntity(BinRestDto dto);

    default BinId mapBinId(Long value) {
        return value != null ? new BinId(value) : null;
    }

    default SiteId mapSiteId(Long value) {
        return value != null ? new SiteId(value) : null;
    }

    default Name mapBinName(String value) {
        return value != null ? new Name(value) : null;
    }

    default FillLevel mapFillLevel(Double value) {
        return value != null ? new FillLevel(value) : null;
    }

    default LastUpdated mapLastUpdated(Instant value) {
        return value != null ? new LastUpdated(value) : null;
    }
}
