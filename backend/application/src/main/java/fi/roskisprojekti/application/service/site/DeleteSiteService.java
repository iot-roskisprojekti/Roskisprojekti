package fi.roskisprojekti.application.service.site;

import fi.roskisprojekti.application.port.in.site.DeleteSitesUseCase;
import fi.roskisprojekti.application.port.out.persistence.SiteRepository;
import fi.roskisprojekti.domain.site.SiteId;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class DeleteSiteService implements DeleteSitesUseCase {
    private final SiteRepository siteRepository;
    @Override
    @Transactional
    public void deleteById(SiteId id) {
        siteRepository.deleteById(id);
    }
}
