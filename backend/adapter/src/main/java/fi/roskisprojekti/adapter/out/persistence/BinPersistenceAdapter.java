package fi.roskisprojekti.adapter.out.persistence;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.BinJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.mapper.BinPersistenceMapper;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.BinJpaRepository;
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
        BinJpaEntity existing = jpaRepository.findById(bin.getBinId().value())
                .orElseThrow(() -> new RuntimeException("Bin not found: " + bin.getBinId().value()));

        existing.setFillLevelPercent(BigDecimal.valueOf(bin.getFillLevel().percent()));
        existing.setLastMeasuredAt(bin.getLastUpdated() != null ? bin.getLastUpdated().value() : null);

        return mapper.toDomainEntity(jpaRepository.save(existing));
    }
}