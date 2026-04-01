package fi.roskisprojekti.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import fi.roskisprojekti.adapter.out.persistence.jpa.entity.SiteJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.mapper.SitePersistenceMapper;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.SiteJpaRepository;
import fi.roskisprojekti.application.port.out.persistence.SiteRepository; // The Port
import fi.roskisprojekti.domain.site.Site; // Pure Domain Model
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SitePersistenceAdapter implements SiteRepository {

    private final SiteJpaRepository jpaRepository;
    private final SitePersistenceMapper siteMapper;

    @Override
    public List<Site> findAllSites() {
        List<SiteJpaEntity> entities = jpaRepository.findAll();

        return entities.stream()
                .map(siteMapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {

        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<Site> findById(Long id) {
        return jpaRepository.findById(id)
                .map(siteMapper::toDomainEntity);
    }

    @Override
    public Site save(Site site) {
        SiteJpaEntity entity = siteMapper.toJpaEntity(site);

        SiteJpaEntity savedEntity = jpaRepository.save(entity);

        return siteMapper.toDomainEntity(savedEntity);
    }


}
