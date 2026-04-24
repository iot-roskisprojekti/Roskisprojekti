package fi.roskisprojekti.application.port.in.site;

import fi.roskisprojekti.domain.entity.site.Site;
import fi.roskisprojekti.domain.entity.site.SiteId;

import java.util.List;
import java.util.Optional;

public interface FindSitesUseCase {
    List<Site> findAllSites();
    List<Site> findByQuery(String query);
    List<Site> findByStatus(); //Onkohan Sitella edes statusta? Hmm... Ei ainakaan vielä.
    Optional<Site> findById(SiteId id);
}
