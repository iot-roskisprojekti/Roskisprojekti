package fi.roskisprojekti.domain.entity.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Employee {
    private final EmployeeId employeeId;
    private final Name name;
    private final Email email;
    private final Phone phone;
}
