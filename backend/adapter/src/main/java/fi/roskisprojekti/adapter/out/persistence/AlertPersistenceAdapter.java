package fi.roskisprojekti.adapter.out.persistence;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.AlertJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.entity.BinJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.mapper.AlertPersistenceMapper;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.AlertJpaRepository;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.BinJpaRepository;
import fi.roskisprojekti.application.port.out.persistence.AlertRepository;
import fi.roskisprojekti.domain.entity.alert.Alert;
import fi.roskisprojekti.domain.entity.alert.AlertState;
import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.alert.AlertId;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AlertPersistenceAdapter implements AlertRepository {
    private final AlertJpaRepository alertJpaRepository;
    private final BinJpaRepository binJpaRepository;
    private final AlertPersistenceMapper alertPersistenceMapper;

    @Override
    public List<Alert> findAll() {
        return alertJpaRepository.findAll().stream()
                .map(alertPersistenceMapper::toDomainEntity)
                .toList();
    }

    @Override
    public Alert save(Alert alert) {
        BinJpaEntity binJpa = binJpaRepository.findById(alert.getBinId().value())
                .orElseThrow(() -> new RuntimeException("Bin not found"));
        AlertJpaEntity jpaEntity = alertPersistenceMapper.toJpaEntity(alert, binJpa);
        return  alertPersistenceMapper.toDomainEntity(alertJpaRepository.save(jpaEntity));
    }

    @Override
    public boolean existsByBinIdAndAlertState(BinId binId, AlertState state) {
        return alertJpaRepository.existsByBinJpaEntity_IdAndAlertState(binId.value(), state);
    }

    @Override
    public Optional<Alert> findById(AlertId alertId) {
        return alertJpaRepository.findById(alertId.value())
                .map(alertPersistenceMapper::toDomainEntity);
    }
}
