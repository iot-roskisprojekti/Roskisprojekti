package org.example.model.dto;


import java.time.LocalDateTime;

public record SiteDto(
        Long id,
        String name,
        String location,
        java.math.BigDecimal capacityLiters,
        Integer fillPercent,
        String status,
        LocalDateTime lastUpdated
) {}