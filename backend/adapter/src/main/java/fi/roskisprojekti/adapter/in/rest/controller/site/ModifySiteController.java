package fi.roskisprojekti.adapter.in.rest.controller.site;

import fi.roskisprojekti.adapter.in.rest.dto.SiteRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.SiteRestMapper;
import fi.roskisprojekti.application.port.in.site.ModifySiteUseCase;
import fi.roskisprojekti.domain.entity.site.Site;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sites")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ModifySiteController {

    private final ModifySiteUseCase modifySiteUseCase;
    private final SiteRestMapper siteRestMapper;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SiteRestDto modifySite(@PathVariable Long id, @RequestBody SiteRestDto siteDto) {

        Site domainSite = siteRestMapper.toDomainEntity(siteDto);

        return siteRestMapper.toRestDto(modifySiteUseCase.modifySite(domainSite));
    }
}
