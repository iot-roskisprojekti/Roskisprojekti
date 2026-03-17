package org.example.controller;

import org.example.model.dto.SiteDto;
import org.example.service.SiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@CrossOrigin(origins = "http://localhost:5173")
public class SiteController {
    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping
    public List<SiteDto> getAllSites() {
        return siteService.getAllSites();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<SiteDto> addSite(@RequestBody SiteDto siteDto){


        return new ResponseEntity<>(siteService.addSite(siteDto), HttpStatus.CREATED);
    }


}