package fi.roskisprojekti.adapter.out.persistence;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.BinJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.entity.SiteJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.mapper.BinPersistenceMapper;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.BinJpaRepository;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.SiteJpaRepository;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.domain.entity.bin.Bin;
import fi.roskisprojekti.domain.entity.bin.BinId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BinPersistenceAdapter implements BinRepository {

    private final BinJpaRepository jpaRepository;
    private final SiteJpaRepository siteJpaRepository; 
    private final BinPersistenceMapper mapper;

    @Override
    public List<Bin> findAllBins() {
        List<BinJpaEntity> bins = jpaRepository.findAll();
        return bins.stream()
                .map(mapper::toDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Bin> findById(BinId id) {
        return jpaRepository.findById(id.value())
                .map(mapper::toDomainEntity);
    }

    @Override
    public void deleteById(BinId id) {
        jpaRepository.deleteById(id.value());
    }

    @Override
public Bin save(Bin bin) {
    System.out.println("Saving bin, binId: " + bin.getBinId());
    System.out.println("Saving bin, siteId: " + bin.getSiteId());
    System.out.println("Saving bin, capacity: " + bin.getCapacity());

    // Uusi bin (binId null) tai olemassa oleva
    BinJpaEntity entity;
    if (bin.getBinId() == null || bin.getBinId().value() == null) {
        // Luo uusi
        entity = mapper.toJpaEntity(bin);
    } else {
        // Päivitä olemassa oleva
        entity = jpaRepository.findById(bin.getBinId().value())
                .orElse(mapper.toJpaEntity(bin));
        entity.setFillLevelPercent(bin.getFillLevel() != null
                ? BigDecimal.valueOf(bin.getFillLevel().percent()) : null);
        entity.setLastMeasuredAt(bin.getLastUpdated() != null
                ? bin.getLastUpdated().value() : null);
        if (bin.getCapacity() != null) {
            entity.setCapacityLiters(BigDecimal.valueOf(bin.getCapacity().value()));
        }
    }

    // Aseta SiteJpaEntity
    if (bin.getSiteId() != null) {
        SiteJpaEntity site = siteJpaRepository.findById(bin.getSiteId().value())
                .orElseThrow(() -> new RuntimeException("Site not found: " + bin.getSiteId().value()));
        entity.setSiteJpaEntity(site);
    }

    return mapper.toDomainEntity(jpaRepository.save(entity));
}
}