package fi.roskisprojekti.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bin", schema = "roskis_db", indexes = {@Index(name = "site_id",
        columnList = "site_id")})
public class BinJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bin_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "site_id", nullable = false)
    private SiteJpaEntity siteJpaEntity;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "capacity_liters", nullable = false, precision = 10, scale = 2)
    private BigDecimal capacityLiters;

    @OneToMany(mappedBy = "binJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AlertJpaEntity> alertEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "binJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<MeasurementJpaEntity> measurementEntities = new LinkedHashSet<>();
    @NotNull
    @ColumnDefault("1000")
    @Column(name = "height_mm", nullable = false)
    private Integer heightMm;
    @ColumnDefault("0.00")
    @Column(name = "fill_level_percent", precision = 5, scale = 2)
    private BigDecimal fillLevelPercent;
    @Column(name = "last_measured_at")
    private Instant lastMeasuredAt;


}