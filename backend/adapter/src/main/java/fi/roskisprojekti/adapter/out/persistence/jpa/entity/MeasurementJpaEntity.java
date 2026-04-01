package fi.roskisprojekti.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "measurement", schema = "roskis_db", indexes = {@Index(name = "bin_id",
        columnList = "bin_id")})
public class MeasurementJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bin_id", nullable = false)
    private BinJpaEntity binJpaEntity;

    @NotNull
    @Column(name = "measured_at", nullable = false)
    private Instant measuredAt;

    @NotNull
    @Column(name = "distance_mm", nullable = false)
    private Integer distanceMm;


}