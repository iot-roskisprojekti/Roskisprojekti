package fi.roskisprojekti.adapter.out.persistence.jpa.entity;

import fi.roskisprojekti.adapter.out.persistence.jpa.converter.MessageStatusConverter;
import fi.roskisprojekti.domain.entity.message.MessageStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "message", schema = "roskis_db", indexes = {
        @Index(name = "task_id",
                columnList = "task_id"),
        @Index(name = "employee_id",
                columnList = "employee_id")})
public class MessageJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private TaskJpaEntity taskJpaEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeJpaEntity employeeJpaEntity;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "sent_time", nullable = false)
    private Instant sentTime;

    @NotNull
    @Convert(converter = MessageStatusConverter.class)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('LÄHETETTY', 'EPÄONNISTUNUT')")
    private MessageStatus status;


}