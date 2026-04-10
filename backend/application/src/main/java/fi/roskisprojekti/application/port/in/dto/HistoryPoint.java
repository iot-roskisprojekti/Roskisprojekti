package fi.roskisprojekti.application.port.in.dto;

import java.time.LocalDate;

public record HistoryPoint(
    LocalDate date,
    int fillLevel,
    boolean emptied
) {}
