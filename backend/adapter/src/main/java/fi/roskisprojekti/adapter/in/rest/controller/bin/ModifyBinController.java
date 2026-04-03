package fi.roskisprojekti.adapter.in.rest.controller.bin;

import fi.roskisprojekti.adapter.in.rest.dto.BinRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.BinRestMapper;
import fi.roskisprojekti.application.port.in.bin.ModifyBinUseCase;
import fi.roskisprojekti.domain.entity.bin.Bin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bins")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ModifyBinController {

    private final ModifyBinUseCase modifyBinUseCase;
    private final BinRestMapper binRestMapper;

    @PutMapping("/{id}")
    public BinRestDto modifyBin(@PathVariable Long id, @RequestBody BinRestDto binDto) {
        Bin domainBin = binRestMapper.toDomainEntity(binDto);
        // Ensure ID consistency if binDto.binId is null or mismatch
        Bin updatedBin = modifyBinUseCase.modifyBin(domainBin);
        return binRestMapper.toRestDto(updatedBin);
    }
}