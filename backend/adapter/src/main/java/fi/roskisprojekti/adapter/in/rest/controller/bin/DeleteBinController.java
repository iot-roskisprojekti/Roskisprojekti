package fi.roskisprojekti.adapter.in.rest.controller.bin;

import fi.roskisprojekti.adapter.in.rest.mapper.BinRestMapper;
import fi.roskisprojekti.application.port.in.bin.DeleteBinUseCase;
import fi.roskisprojekti.domain.bin.BinId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bins")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class DeleteBinController {

    private final DeleteBinUseCase deleteBinUseCase;
    private final BinRestMapper binRestMapper;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBin(@PathVariable Long id) {
        deleteBinUseCase.deleteBinById(new BinId(id));
    }
}
