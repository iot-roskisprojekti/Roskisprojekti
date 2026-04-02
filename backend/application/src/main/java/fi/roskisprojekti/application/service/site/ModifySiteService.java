package fi.roskisprojekti.application.service.site;

import fi.roskisprojekti.application.port.in.site.ModifySiteUseCase;
import fi.roskisprojekti.application.port.out.persistence.SiteRepository;
import fi.roskisprojekti.domain.site.Site;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ModifySiteService implements ModifySiteUseCase {
    private final SiteRepository siteRepository;

    @Override
    @Transactional
    public Site modifySite(Site site) {
        return siteRepository.save(site);
    }
}
