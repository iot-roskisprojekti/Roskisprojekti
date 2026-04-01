package fi.roskisprojekti.adapter.in.rest.mapper;

import fi.roskisprojekti.adapter.in.rest.dto.SiteRestDto;
import fi.roskisprojekti.domain.site.Site;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SiteRestMapper {

    @Mapping(target = "id", source = "siteId.value")
    @Mapping(target = "name", source = "name.value")
    @Mapping(target = "location", source = "location.value")
    SiteRestDto toRestDto(Site site);
}
