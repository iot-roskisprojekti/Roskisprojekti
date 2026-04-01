package fi.roskisprojekti.application.port.in.site;

import fi.roskisprojekti.domain.site.Site;
import fi.roskisprojekti.domain.site.SiteId;

import java.util.List;
import java.util.Optional;

public interface FindSitesUseCase {
    List<Site> findAllSites();
    List<Site> findByQuery(String query);
    List<Site> findByStatus(); //Onkohan Sitella edes statusta? Hmm... Ei ainakaan vielä.
    Optional<Site> findById(SiteId id);
}
