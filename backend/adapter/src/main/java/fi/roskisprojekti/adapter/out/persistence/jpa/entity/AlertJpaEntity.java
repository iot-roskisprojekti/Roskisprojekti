package fi.roskisprojekti.adapter.out.persistence.jpa.entity;

import fi.roskisprojekti.adapter.out.persistence.jpa.converter.AlertStateConverter;
import fi.roskisprojekti.adapter.out.persistence.jpa.converter.AlertTypeConverter;
import fi.roskisprojekti.domain.alert.AlertState;
import fi.roskisprojekti.domain.alert.AlertType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "alert", schema = "roskis_db", indexes = {@Index(name = "bin_id",
        columnList = "bin_id")})
public class AlertJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bin_id", nullable = false)
    private BinJpaEntity binJpaEntity;

    @NotNull
    @Convert(converter = AlertTypeConverter.class)
    @JdbcTypeCode(Types.CHAR)
    @Column(
            name = "alert_type",
            nullable = false
    )
    private AlertType alertType;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Convert(converter = AlertStateConverter.class)
    @JdbcTypeCode(Types.CHAR)
    @Column(name = "state", nullable = false)
    private AlertState alertState;

    @Column(name = "closed_at")
    private Instant closedAt;

    @OneToOne(mappedBy = "alertJpaEntity")
    private TaskJpaEntity taskJpaEntity;


}