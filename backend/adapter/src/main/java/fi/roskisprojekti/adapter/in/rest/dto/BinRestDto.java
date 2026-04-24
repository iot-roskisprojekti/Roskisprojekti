package fi.roskisprojekti.adapter.in.rest.dto;

import fi.roskisprojekti.domain.entity.bin.BinStatus;

import java.time.Instant;

public record BinRestDto(
        Long binId,
        Long siteId,
        String name,
        Double fillLevel,
        Double capacityLiters,
        BinStatus status,
        Instant lastUpdated
) {}
