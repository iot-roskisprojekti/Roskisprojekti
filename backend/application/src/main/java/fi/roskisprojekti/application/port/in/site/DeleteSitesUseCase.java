package fi.roskisprojekti.application.port.in.site;

import fi.roskisprojekti.domain.site.SiteId;

public interface DeleteSitesUseCase {
    void deleteById(SiteId id);
}
