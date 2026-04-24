package fi.roskisprojekti.application.port.in.site;

import fi.roskisprojekti.domain.entity.site.SiteId;

public interface DeleteSitesUseCase {
    void deleteById(SiteId id);
}
