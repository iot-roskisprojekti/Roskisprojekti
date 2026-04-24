package fi.roskisprojekti.adapter.in.rest.dto;

import java.time.Instant;

public record TaskRestDto(Long taskId, Long alertId, Instant createdAt, String status, Instant assignedAt, Instant doneAt) {
}
