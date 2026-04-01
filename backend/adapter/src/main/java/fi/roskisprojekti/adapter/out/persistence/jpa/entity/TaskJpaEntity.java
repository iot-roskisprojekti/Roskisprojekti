package fi.roskisprojekti.adapter.out.persistence.jpa.entity;

import fi.roskisprojekti.adapter.out.persistence.jpa.converter.TaskStatusConverter;
import fi.roskisprojekti.domain.task.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "task", schema = "roskis_db", indexes = {@Index(name = "assigned_employee_id",
        columnList = "assigned_employee_id")}, uniqueConstraints = {@UniqueConstraint(name = "alert_id",
        columnNames = {"alert_id"})})
public class TaskJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alert_id", nullable = false)
    private AlertJpaEntity alertJpaEntity;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Convert(converter = TaskStatusConverter.class)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('AVOIN', 'KESKEN', 'VALMIS')")
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_employee_id")
    private EmployeeJpaEntity assignedEmployeeJpaEntity;

    @Column(name = "assigned_at")
    private Instant assignedAt;

    @Column(name = "done_at")
    private Instant doneAt;

    @OneToMany(mappedBy = "taskJpaEntity")
    private Set<MessageJpaEntity> messageEntities = new LinkedHashSet<>();


}