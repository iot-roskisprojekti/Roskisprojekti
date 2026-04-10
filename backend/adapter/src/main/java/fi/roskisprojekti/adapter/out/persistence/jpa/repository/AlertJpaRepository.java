package fi.roskisprojekti.adapter.out.persistence.jpa.repository;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.AlertJpaEntity;
import fi.roskisprojekti.domain.entity.alert.AlertState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertJpaRepository extends JpaRepository<AlertJpaEntity, Long> {
    boolean existsByBinJpaEntity_IdAndAlertState(Long binId, AlertState alertState);
}

