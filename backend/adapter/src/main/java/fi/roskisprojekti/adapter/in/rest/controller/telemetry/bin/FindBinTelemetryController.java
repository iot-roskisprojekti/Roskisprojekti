package fi.roskisprojekti.adapter.in.rest.controller.telemetry.bin;

import fi.roskisprojekti.adapter.in.rest.dto.BinTelemetryRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.BinTelemetryRestMapper;
import fi.roskisprojekti.application.port.in.bin.FindBinTelemetryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bins/{binId}/telemetry")
@CrossOrigin(origins = "http://localhost:5173")
public class FindBinTelemetryController {

    private final FindBinTelemetryUseCase findBinTelemetryUseCase;
    private final BinTelemetryRestMapper restMapper;

    @GetMapping
    public List<BinTelemetryRestDto> getBinTelemetryHistory(@PathVariable Long binId) {
        return findBinTelemetryUseCase.findByBinId(restMapper.toBinId(binId))
                .stream()
                .map(restMapper::toRestDto)
                .toList();
    }
}