package fi.roskisprojekti.application.port.in.telemetry.bin;


import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;

public interface HandleInBoundTelemetryUseCase {
    void handleBinTelemetry(BinTelemetry binTelemetry);
}
