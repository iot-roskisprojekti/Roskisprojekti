package fi.roskisprojekti.adapter.in.rest.mapper;

import fi.roskisprojekti.adapter.in.rest.dto.BinRestDto;
import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.entity.bin.*;
import fi.roskisprojekti.domain.entity.site.SiteId;
import fi.roskisprojekti.domain.entity.bin.Capacity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BinRestMapper {

    @Mapping(target = "binId", source = "binId.value")
    @Mapping(target = "siteId", source = "siteId.value")
    @Mapping(target = "fillLevel", source = "fillLevel.percent")
    @Mapping(target = "capacityLiters", source = "capacity.value")
    @Mapping(target = "status", source = "binStatus")
    @Mapping(target = "lastUpdated", source = "lastUpdated.value")
    @Mapping(target = "name", source = "name.value")
    BinRestDto toRestDto(Bin bin);

    @Mapping(target = "binId", source = "binId")
    @Mapping(target = "siteId", source = "siteId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "fillLevel", source = "fillLevel")
    @Mapping(target = "lastUpdated", source = "lastUpdated")
    @Mapping(target = "capacity", source = "capacityLiters")
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

    default SingleTimeStamp mapLastUpdated(Instant value) {
        return value != null ? new SingleTimeStamp(value) : null;
    }

    default Capacity mapCapacity(Double value) {
    return value != null ? new Capacity(value) : new Capacity(0.0);
}
}
