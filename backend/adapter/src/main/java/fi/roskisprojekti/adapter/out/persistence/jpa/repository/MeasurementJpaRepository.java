package fi.roskisprojekti.adapter.out.persistence.jpa.repository;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.MeasurementJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.List;

public interface MeasurementJpaRepository extends JpaRepository<MeasurementJpaEntity, Long> {
    List<MeasurementJpaEntity> findByBinJpaEntity_IdOrderByMeasuredAtAsc(Long binId);


    List<MeasurementJpaEntity> findByBinJpaEntity_Id(Long binId);


}
