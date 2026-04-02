package fi.roskisprojekti.application.port.out.persistence;

import fi.roskisprojekti.domain.site.Site;
import fi.roskisprojekti.domain.site.SiteId;

import java.util.List;
import java.util.Optional;

public interface SiteRepository {
    List<Site> findAllSites();
    void deleteById(SiteId id);
    Optional<Site> findById(SiteId id); //
    Site save(Site site);


}
