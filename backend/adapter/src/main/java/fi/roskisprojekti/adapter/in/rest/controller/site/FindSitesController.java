package fi.roskisprojekti.adapter.in.rest.controller.site;

import fi.roskisprojekti.adapter.in.rest.dto.SiteRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.SiteRestMapper;
import fi.roskisprojekti.application.port.in.site.FindSitesUseCase;
import fi.roskisprojekti.domain.entity.site.Site;
import fi.roskisprojekti.domain.entity.site.SiteId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class FindSitesController {
    private final FindSitesUseCase findSitesUseCase;
    private final SiteRestMapper siteRestMapper;


    @GetMapping
    public List<SiteRestDto> getAllSites() {
        List<Site> sites = findSitesUseCase.findAllSites();

        return sites.stream()
                .map(siteRestMapper::toRestDto)
                .toList();
    }

    @GetMapping("/{id}")
    public SiteRestDto getSiteById(@PathVariable Long id) {
        return findSitesUseCase.findById(new SiteId(id))
                .map(siteRestMapper::toRestDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Site not found"));
    }

}