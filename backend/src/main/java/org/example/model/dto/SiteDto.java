package org.example.model.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SiteDto(
        Long id,
        String name,
        String location,
        BigDecimal capacity,
        Integer fillPercent,
        String status,
        LocalDateTime lastUpdated
) {}