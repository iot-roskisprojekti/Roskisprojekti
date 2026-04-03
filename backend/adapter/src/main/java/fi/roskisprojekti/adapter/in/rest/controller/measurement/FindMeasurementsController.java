package fi.roskisprojekti.adapter.in.rest.controller.measurement;

import java.util.List;

import fi.roskisprojekti.adapter.in.rest.dto.MeasurementRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.MeasurementRestMapper;
import fi.roskisprojekti.application.port.in.measurement.FindMeasurementsUseCase;
import fi.roskisprojekti.domain.entity.measurement.Measurement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/measurements")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class FindMeasurementsController {
    private final FindMeasurementsUseCase findMeasurementsUseCase;
    private final MeasurementRestMapper measurementRestMapper;

    @GetMapping
    public List<MeasurementRestDto> getAllMeasurements() {
        List<Measurement> measurements = findMeasurementsUseCase.findAllMeasurements();
        return measurements.stream()
                .map(measurementRestMapper::toRestDto)
                .toList();
    }

}