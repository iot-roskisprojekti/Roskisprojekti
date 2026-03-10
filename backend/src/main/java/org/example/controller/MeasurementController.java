package org.example.controller;

import java.util.List;

import org.example.model.dto.MeasurementDto;
import org.example.service.MeasurementService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/measurements")
@CrossOrigin(origins = "http://localhost:5173")
public class MeasurementController {
    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    public List<MeasurementDto> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }

}