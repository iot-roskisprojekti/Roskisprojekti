package fi.roskisprojekti.application.service.site;

import fi.roskisprojekti.application.port.in.site.CreateSiteUseCase;
import fi.roskisprojekti.application.port.out.persistence.SiteRepository;
import fi.roskisprojekti.domain.entity.site.Site;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CreateSiteService implements CreateSiteUseCase {
    private final SiteRepository siteRepository;
    @Override
    @Transactional
    public Site createSite(Site site) {
        return siteRepository.save(site);
    }
}
