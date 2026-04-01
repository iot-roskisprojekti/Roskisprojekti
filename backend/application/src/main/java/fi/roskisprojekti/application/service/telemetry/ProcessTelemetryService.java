package fi.roskisprojekti.application.service.telemetry;

import lombok.RequiredArgsConstructor;
import fi.roskisprojekti.application.port.in.telemetry.ProcessTelemetryUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.application.port.out.persistence.MeasurementRepository;
import fi.roskisprojekti.domain.bin.Bin;
import fi.roskisprojekti.domain.bin.BinId;
import fi.roskisprojekti.domain.measurement.Distance;
import fi.roskisprojekti.domain.measurement.MeasuredAt;
import fi.roskisprojekti.domain.measurement.Measurement;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@RequiredArgsConstructor

public class ProcessTelemetryService implements ProcessTelemetryUseCase {

    private final MeasurementRepository measurementRepository;
    private final BinRepository binRepository;
    //Aika turhake? Vai onko? Tuleeko jossain vaiheessa telemetriaan lisääkin tavaraa?
    @Override
    @Transactional
    public void processTelemetry(Long binId, int distance, Instant measuredAt) {
        Bin bin = binRepository.findById(new BinId(binId))
                .orElseThrow(() -> new RuntimeException("Bin not found: " + binId));


        Measurement measurement = new Measurement(
                new BinId(binId),
                new Distance(distance),
                new MeasuredAt(measuredAt)
        );

        bin.updateFillLevelFromCensor(measurement.getDistance());


        measurementRepository.save(measurement);
        binRepository.save(bin);
    }

}