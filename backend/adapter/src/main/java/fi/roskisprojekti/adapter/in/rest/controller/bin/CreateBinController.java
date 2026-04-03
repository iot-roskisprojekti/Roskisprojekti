package fi.roskisprojekti.adapter.in.rest.controller.bin;

import fi.roskisprojekti.adapter.in.rest.dto.BinRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.BinRestMapper;
import fi.roskisprojekti.application.port.in.bin.CreateBinUseCase;
import fi.roskisprojekti.domain.entity.bin.Bin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bins")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class CreateBinController {

    private final CreateBinUseCase createBinUseCase;
    private final BinRestMapper binRestMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BinRestDto createBin(@RequestBody BinRestDto binDto) {
        Bin domainBin = binRestMapper.toDomainEntity(binDto);
        Bin savedBin = createBinUseCase.createBin(domainBin);
        return binRestMapper.toRestDto(savedBin);
    }
}