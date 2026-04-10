package fi.roskisprojekti.adapter.in.rest.dto;

import java.time.Instant;

public record AlertRestDto(Long id,
                           Long binId,
                           String type,
                           String state,
                           Instant triggeredAt,
                           Instant closedAt) {
}
