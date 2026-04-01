package fi.roskisprojekti.application.port.out.persistence;

import fi.roskisprojekti.domain.site.Site;

import java.util.List;
import java.util.Optional;

public interface SiteRepository {
    List<Site> findAllSites();
    void deleteById(Long id);
    Optional<Site> findById(Long id); //
    Site save(Site site);
}
