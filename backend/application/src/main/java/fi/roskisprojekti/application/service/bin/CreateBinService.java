package fi.roskisprojekti.application.service.bin;

import fi.roskisprojekti.application.port.in.bin.CreateBinUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.domain.bin.Bin;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateBinService implements CreateBinUseCase {
    private final BinRepository binRepository;
    @Override
    public Bin createBin(Bin bin) {
        return binRepository.save(bin);
    }
}
