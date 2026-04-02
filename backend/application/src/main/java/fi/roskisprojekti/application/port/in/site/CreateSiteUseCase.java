package fi.roskisprojekti.application.port.in.site;

import fi.roskisprojekti.domain.site.Site;

public interface CreateSiteUseCase {
    Site createSite(Site site);
}
