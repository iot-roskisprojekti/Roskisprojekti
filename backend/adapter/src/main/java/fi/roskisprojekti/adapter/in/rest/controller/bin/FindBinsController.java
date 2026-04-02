package fi.roskisprojekti.adapter.in.rest.controller.bin;

import fi.roskisprojekti.adapter.in.rest.dto.BinRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.BinRestMapper;
import fi.roskisprojekti.application.port.in.bin.FindBinsUseCase;
import fi.roskisprojekti.domain.bin.BinId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class FindBinsController {

    private final FindBinsUseCase findBinsUseCase;
    private final BinRestMapper binRestMapper;

    @GetMapping
    public List<BinRestDto> getAllBins() {
        return findBinsUseCase.findAllBins().stream()
                .map(binRestMapper::toRestDto)
                .toList();
    }

    @GetMapping("/{id}")
    public BinRestDto getBinById(@PathVariable Long id) {
        return binRestMapper.toRestDto(findBinsUseCase.findById(new BinId(id)));
    }
}