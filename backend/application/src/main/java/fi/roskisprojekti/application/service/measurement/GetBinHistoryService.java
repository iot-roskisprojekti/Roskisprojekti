package fi.roskisprojekti.application.service.measurement;
/**
import fi.roskisprojekti.application.port.out.persistence.MeasurementRepository;
import fi.roskisprojekti.application.port.in.dto.BinHistoryDto;
import fi.roskisprojekti.application.port.in.dto.HistoryPoint;
import fi.roskisprojekti.domain.entity.measurement.Measurement;
import fi.roskisprojekti.domain.entity.measurement.Distance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class GetBinHistoryService {

    private final MeasurementRepository measurementRepository;

    public BinHistoryDto getHistory(Long binId) {

        List<Measurement> measurements =
                measurementRepository.findByBinIdOrderByTimestampAsc(binId);

        Map<LocalDate, List<Measurement>> grouped =
                measurements.stream()
                        .collect(Collectors.groupingBy(
                                m -> m.getMeasuredAt().value()
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate()
                        ));

        List<HistoryPoint> history = new ArrayList<>();

        for (var entry : grouped.entrySet()) {

            LocalDate date = entry.getKey();
            List<Measurement> day = entry.getValue();

            int avgFill = (int) day.stream()
                    .mapToInt(m -> convertDistanceToFillLevel(m.getDistance()))
                    .average()
                    .orElse(0);

            boolean emptied = day.stream()
                    .anyMatch(m -> convertDistanceToFillLevel(m.getDistance()) < 10);

            history.add(new HistoryPoint(date, avgFill, emptied));
        }

        history.sort(Comparator.comparing(HistoryPoint::date));

        return new BinHistoryDto(history);
    }
    private static final double MAX_DISTANCE = 1200.0;
    private int convertDistanceToFillLevel(Distance distance) {
       double percent = 100.0 - (distance.value() / MAX_DISTANCE) * 100.0;
        return (int) Math.max(0, Math.min(100, percent));
    }

    
}
*/