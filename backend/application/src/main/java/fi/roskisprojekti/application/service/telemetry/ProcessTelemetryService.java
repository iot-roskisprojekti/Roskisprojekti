package fi.roskisprojekti.application.service.telemetry;

import lombok.RequiredArgsConstructor;
import fi.roskisprojekti.application.port.in.telemetry.ProcessTelemetryUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.application.port.out.persistence.MeasurementRepository;
import fi.roskisprojekti.domain.entity.bin.Bin;
import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.measurement.Distance;
import fi.roskisprojekti.domain.entity.measurement.MeasuredAt;
import fi.roskisprojekti.domain.entity.measurement.Measurement;
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


        Measurement measurement = Measurement.createWithoutId(
                bin.getBinId(),
                new Distance(distance),
                new MeasuredAt(measuredAt)
                );

        bin.updateFillLevelFromCensor(measurement.getDistance(), measuredAt);


        measurementRepository.save(measurement);
        binRepository.save(bin);
    }

}