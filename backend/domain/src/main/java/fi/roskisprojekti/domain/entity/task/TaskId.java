package fi.roskisprojekti.domain.entity.task;


import static fi.roskisprojekti.domain.validation.DomainValidator.isValidID;

public record TaskId(Long value) {
    public TaskId{
        isValidID(value, "Task id");
    }
}
