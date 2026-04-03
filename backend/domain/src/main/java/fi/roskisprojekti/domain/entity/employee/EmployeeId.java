package fi.roskisprojekti.domain.entity.employee;

import java.util.Objects;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidID;

public record EmployeeId(Long value) {
    public EmployeeId {
        isValidID(value, "Employee id");
    }
}
