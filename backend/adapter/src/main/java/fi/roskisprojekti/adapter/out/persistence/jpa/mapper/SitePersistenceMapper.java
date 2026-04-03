package fi.roskisprojekti.adapter.out.persistence.jpa.mapper;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.SiteJpaEntity;
import fi.roskisprojekti.domain.entity.site.Location;
import fi.roskisprojekti.domain.entity.site.Name;
import fi.roskisprojekti.domain.entity.site.Site;
import fi.roskisprojekti.domain.entity.site.SiteId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SitePersistenceMapper {
    @Mapping(target = "siteId", source = "id")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "name", source = "name")
    Site toDomainEntity(SiteJpaEntity entity);

    @Mapping(target = "id", source = "siteId.value")
    @Mapping(target = "name", source = "name.value")
    @Mapping(target = "location", source = "location.value")
    @Mapping(target = "binEntities", ignore = true)
    SiteJpaEntity toJpaEntity(Site domain);

    default SiteId mapToSiteId(Long value) { return new SiteId(value); }
    default Name mapToName(String value) { return new Name(value); }
    default Location mapToLocation(String value) { return new Location(value); }


}