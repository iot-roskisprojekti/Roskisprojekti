package fi.roskisprojekti.application.service.bin;

import fi.roskisprojekti.application.port.in.bin.DeleteBinUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.domain.entity.bin.BinId;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class DeleteBinService implements DeleteBinUseCase {
    private final BinRepository binRepository;

    @Override
    @Transactional
    public void deleteBinById(BinId id) {
        binRepository.deleteById(id);
    }
}
