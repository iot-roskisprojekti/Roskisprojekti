package fi.roskisprojekti.application.port.in.dto;

import java.util.List;

import fi.roskisprojekti.application.port.in.dto.HistoryPoint;

public record BinHistoryDto(
    List<HistoryPoint> history
) {}
