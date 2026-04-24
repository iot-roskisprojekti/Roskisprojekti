package fi.roskisprojekti.adapter.in.rest.mapper;

import fi.roskisprojekti.adapter.in.rest.dto.SiteRestDto;
import fi.roskisprojekti.domain.entity.site.Location;
import fi.roskisprojekti.domain.entity.site.Name;
import fi.roskisprojekti.domain.entity.site.Site;
import fi.roskisprojekti.domain.entity.site.SiteId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SiteRestMapper {

    @Mapping(target = "id", source = "siteId.value")
    @Mapping(target = "name", source = "name.value")
    @Mapping(target = "location", source = "location.value")
    SiteRestDto toRestDto(Site site);

    @Mapping(target = "siteId", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "location", source = "location")
    Site toDomainEntity(SiteRestDto dto);


    default SiteId mapSiteId(Long value) {
        return value != null ? new SiteId(value) : null;
    }

    default Name mapName(String value) {
        return value != null ? new Name(value) : null;
    }

    default Location mapLocation(String value) {
        return value != null ? new Location(value) : null;
    }
}
