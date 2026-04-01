package fi.roskisprojekti.adapter.in.rest.mapper;

import fi.roskisprojekti.adapter.in.rest.dto.BinRestDto;
import fi.roskisprojekti.domain.bin.Bin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BinRestMapper {

    @Mapping(target = "binId", source = "binId.value")
    @Mapping(target = "siteId", source = "siteId.value")
    @Mapping(target = "fillLevel", source = "fillLevel.percent")
    @Mapping(target = "status", expression = "java(bin.getStatus())")
    @Mapping(target = "lastUpdated", source = "lastUpdated.value")
    BinRestDto toRestDto(Bin bin);
}
