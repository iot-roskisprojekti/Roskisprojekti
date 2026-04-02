package fi.roskisprojekti.application.service.bin;

import fi.roskisprojekti.application.port.in.bin.ModifyBinUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.domain.bin.Bin;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ModifyBinService implements ModifyBinUseCase {
    private final BinRepository binRepository;
    @Override
    @Transactional
    public Bin modifyBin(Bin bin) {
        return binRepository.save(bin);
    }
}
