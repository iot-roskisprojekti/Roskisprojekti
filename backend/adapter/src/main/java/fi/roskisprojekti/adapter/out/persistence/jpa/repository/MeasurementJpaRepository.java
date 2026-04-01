package fi.roskisprojekti.adapter.out.persistence.jpa.repository;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.MeasurementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementJpaRepository extends JpaRepository<MeasurementJpaEntity, Long> {



}
