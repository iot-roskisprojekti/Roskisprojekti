package fi.roskisprojekti.application.service.site;

import fi.roskisprojekti.application.port.in.site.FindSitesUseCase;
import fi.roskisprojekti.application.port.out.persistence.SiteRepository;
import fi.roskisprojekti.domain.site.Site;
import fi.roskisprojekti.domain.site.SiteId;

import java.util.List;
import java.util.Optional;

public class FindSitesService implements FindSitesUseCase {
    private final SiteRepository siteRepository;

    public FindSitesService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public List<Site> findAllSites() {
        return siteRepository.findAllSites();
    }

    @Override
    public List<Site> findByQuery(String query) {
        return List.of();
    }

    @Override
    public List<Site> findByStatus() {
        return List.of();
    }

    @Override
    public Optional<Site> findById(SiteId id) {
        return siteRepository.findById(id.value());
    }
}
