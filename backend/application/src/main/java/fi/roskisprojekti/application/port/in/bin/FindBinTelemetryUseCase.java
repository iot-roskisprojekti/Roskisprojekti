package fi.roskisprojekti.application.port.in.bin;

import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;
import fi.roskisprojekti.domain.entity.bin.BinId;
import java.util.List;


public interface FindBinTelemetryUseCase {
    public List<BinTelemetry> findByBinId(BinId bindId);
    public List<BinTelemetry> findAll();
}
