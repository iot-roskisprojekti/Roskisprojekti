package fi.roskisprojekti.application.port.out.persistence;

import fi.roskisprojekti.domain.entity.site.Site;
import fi.roskisprojekti.domain.entity.site.SiteId;

import java.util.List;
import java.util.Optional;

public interface SiteRepository {
    List<Site> findAllSites();
    void deleteById(SiteId id);
    Optional<Site> findById(SiteId id); //
    Site save(Site site);


}
