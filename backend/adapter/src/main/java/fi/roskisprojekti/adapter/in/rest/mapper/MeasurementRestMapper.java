package fi.roskisprojekti.adapter.in.rest.mapper;

import fi.roskisprojekti.adapter.in.rest.dto.MeasurementRestDto;
import fi.roskisprojekti.domain.entity.measurement.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeasurementRestMapper {

    @Mapping(target = "binId", source = "binId.value")
    @Mapping(target = "distance", source = "distance.value")
    @Mapping(target = "measuredAt", source = "measuredAt.value")
    MeasurementRestDto toRestDto(Measurement measurement);
}
