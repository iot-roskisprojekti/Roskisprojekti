package fi.roskisprojekti.adapter.in.rest;

import fi.roskisprojekti.adapter.in.rest.dto.SiteRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.SiteRestMapper;
import fi.roskisprojekti.application.port.in.site.FindSitesUseCase;
import fi.roskisprojekti.domain.site.Site;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}