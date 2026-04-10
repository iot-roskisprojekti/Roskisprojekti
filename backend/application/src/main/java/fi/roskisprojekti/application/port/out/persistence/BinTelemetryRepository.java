package fi.roskisprojekti.application.port.out.persistence;

import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;

import java.util.List;

public interface BinTelemetryRepository {
    void save(BinTelemetry telemetry);
    List<BinTelemetry> findAllBinTelemetry();
    List<BinTelemetry> findAllTelemetryByBinId(BinId bindId);

}
