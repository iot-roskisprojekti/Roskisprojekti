package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.SiteDto;
import org.example.model.entity.MeasurementEntity;
import org.example.model.entity.SiteEntity;
import org.example.mapper.SiteMapper;
import org.example.repository.MeasurementRepository;
import org.example.repository.SiteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteService {

    private final SiteRepository siteRepository;
    private final MeasurementRepository measurementRepository;
    private final SiteMapper siteMapper;

    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }

    @Transactional
    public SiteDto addSite(SiteDto siteDto){
        SiteEntity newSite = new SiteEntity();

        siteMapper.updateEntityFromDto(siteDto, newSite);

        return siteMapper.toDto(siteRepository.save(newSite
        ), null);
    }

    @Transactional
    public SiteDto modifySite(Long id, SiteDto siteDto) {

        SiteEntity site = siteRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Id " + id + " doesn't exist in the database")) ;

        siteMapper.updateEntityFromDto(siteDto, site);

        return siteMapper.toDto(siteRepository.save(site), null);
    }

    public List<SiteDto> getAllSites() {
        List<SiteEntity> entities = siteRepository.findAll();

        return entities.stream().map(site -> {
            MeasurementEntity latest = measurementRepository
                    .findLatestMeasurement(site.getId())
                    .orElse(null);

            return siteMapper.toDto(site, latest);
        }).toList();
    }



}