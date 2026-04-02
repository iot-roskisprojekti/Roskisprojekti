package fi.roskisprojekti.application.service.site;

import fi.roskisprojekti.application.port.in.site.FindSitesUseCase;
import fi.roskisprojekti.application.port.out.persistence.SiteRepository;
import fi.roskisprojekti.domain.site.Site;
import fi.roskisprojekti.domain.site.SiteId;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FindSitesService implements FindSitesUseCase {
    private final SiteRepository siteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Site> findAllSites() {
        return siteRepository.findAllSites();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Site> findByQuery(String query) {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Site> findByStatus() {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Site> findById(SiteId id) {
        return siteRepository.findById(id);
    }
}
