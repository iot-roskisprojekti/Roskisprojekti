package org.example.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employee", schema = "roskis_db", uniqueConstraints = {@UniqueConstraint(name = "email",
        columnNames = {"email"})})
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 50)
    @Column(name = "phone", length = 50)
    private String phone;
    @NonNull
    @OneToMany(mappedBy = "employeeEntity")
    private Set<MessageEntity> messages = new LinkedHashSet<>();
    @NonNull
    @OneToMany
    @JoinColumn(name = "assigned_employee_id")
    private Set<TaskEntity> tasks = new LinkedHashSet<>();


}