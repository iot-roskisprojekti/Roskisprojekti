package fi.roskisprojekti.bootstrap.domain;

import fi.roskisprojekti.application.port.in.site.CreateSiteUseCase;
import fi.roskisprojekti.application.port.in.site.DeleteSitesUseCase;
import fi.roskisprojekti.application.port.in.site.FindSitesUseCase;
import fi.roskisprojekti.application.port.in.site.ModifySiteUseCase;
import fi.roskisprojekti.application.port.out.persistence.SiteRepository;
import fi.roskisprojekti.application.service.site.CreateSiteService;
import fi.roskisprojekti.application.service.site.DeleteSiteService;
import fi.roskisprojekti.application.service.site.FindSitesService;
import fi.roskisprojekti.application.service.site.ModifySiteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SiteDomainConfig {
    @Bean
    public FindSitesUseCase findSitesUseCase(SiteRepository siteRepository) {
        return new FindSitesService(siteRepository);
    }
    @Bean
    public CreateSiteUseCase createSiteUseCase(SiteRepository siteRepository) {
        return new CreateSiteService(siteRepository);
    }

    @Bean
    public ModifySiteUseCase modifySiteUseCase(SiteRepository siteRepository) {
        return new ModifySiteService(siteRepository);
    }

    @Bean
    public DeleteSitesUseCase deleteSitesUseCase(SiteRepository siteRepository) {
        return new DeleteSiteService(siteRepository);
    }
}