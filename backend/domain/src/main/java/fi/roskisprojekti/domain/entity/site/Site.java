package fi.roskisprojekti.domain.entity.site;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Site {
    private final SiteId siteId;
    private final Location location;
    private final Name name;


}
