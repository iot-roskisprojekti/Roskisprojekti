package fi.roskisprojekti.adapter.in.rest.controller.bin;

import fi.roskisprojekti.adapter.in.rest.mapper.BinTelemetryRestMapper;
import fi.roskisprojekti.application.port.in.dto.BinHistoryDto;
import fi.roskisprojekti.application.service.measurement.GetBinHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/bins")
@RequiredArgsConstructor
public class GetBinHistoryController {

    private final GetBinHistoryService service;
    private final BinTelemetryRestMapper binTelemetryRestMapper;

    @GetMapping("/{id}/history")
    public BinHistoryDto getHistory(@PathVariable Long id) {
        return service.getHistory(binTelemetryRestMapper.toBinId(id));
    }
}
