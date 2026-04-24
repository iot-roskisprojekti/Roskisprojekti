package fi.roskisprojekti.application.service.bin;

import fi.roskisprojekti.application.port.in.bin.FindBinTelemetryUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinTelemetryRepository;
import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindBinTelemetryService implements FindBinTelemetryUseCase {
    private final BinTelemetryRepository binTelemetryRepository;

    @Override
    public List<BinTelemetry> findByBinId(BinId bindId) {
        return binTelemetryRepository.findAllTelemetryByBinId(bindId);
    }

    @Override
    public List<BinTelemetry> findAll() {
        return List.of();
    }
}
