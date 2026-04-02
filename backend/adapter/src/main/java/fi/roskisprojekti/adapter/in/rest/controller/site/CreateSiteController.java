package fi.roskisprojekti.adapter.in.rest.controller.site;

import fi.roskisprojekti.adapter.in.rest.dto.SiteRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.SiteRestMapper;
import fi.roskisprojekti.application.port.in.site.CreateSiteUseCase;
import fi.roskisprojekti.domain.site.Site;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sites")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class CreateSiteController {
    private final CreateSiteUseCase createSiteUseCase;
    private final SiteRestMapper siteRestMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SiteRestDto createSite(@RequestBody SiteRestDto siteDto) {
        Site domainSite = siteRestMapper.toDomainEntity(siteDto);


        return siteRestMapper.toRestDto(createSiteUseCase.createSite(domainSite));
    }

}
