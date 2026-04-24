package fi.roskisprojekti.domain.event.task;

import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.task.TaskId;

import java.time.Instant;

public record BinEmptyingTaskCreatedEvent(TaskId taskId,
                                          BinId binId,
                                          Instant occurredAt) implements TaskDomainEvent {
}
