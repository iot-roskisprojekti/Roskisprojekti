package fi.roskisprojekti.adapter.in.rest.controller.alert;

import fi.roskisprojekti.adapter.in.rest.dto.AlertRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.AlertRestMapper;
import fi.roskisprojekti.application.port.in.alert.ManageAlertsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class FindAlertsController {

    private final ManageAlertsUseCase manageAlertsUseCase;
    private final AlertRestMapper alertRestMapper;

    @GetMapping
    public List<AlertRestDto> getAllAlerts() {
        return manageAlertsUseCase.findAll().stream()
                .map(alertRestMapper::toRestDto)
                .toList();
    }


}