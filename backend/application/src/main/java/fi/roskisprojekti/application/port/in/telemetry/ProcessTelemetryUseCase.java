package fi.roskisprojekti.application.port.in.telemetry;


import java.time.Instant;

public interface ProcessTelemetryUseCase {
    void processTelemetry(Long binId, int distance, Instant measuredAt);
}
