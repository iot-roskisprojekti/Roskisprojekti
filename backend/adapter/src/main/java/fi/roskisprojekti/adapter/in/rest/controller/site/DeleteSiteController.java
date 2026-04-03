package fi.roskisprojekti.adapter.in.rest.controller.site;

import fi.roskisprojekti.adapter.in.rest.mapper.SiteRestMapper;
import fi.roskisprojekti.application.port.in.site.DeleteSitesUseCase;
import fi.roskisprojekti.domain.entity.site.SiteId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sites")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class DeleteSiteController {

    private final DeleteSitesUseCase deleteSitesUseCase;
    private final SiteRestMapper siteRestMapper;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSite(@PathVariable Long id) {
        deleteSitesUseCase.deleteById(new SiteId(id));
    }
}