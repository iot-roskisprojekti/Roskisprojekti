package fi.roskisprojekti.application.port.in.bin;

import fi.roskisprojekti.domain.entity.bin.Bin;

public interface CreateBinUseCase {
    Bin createBin(Bin bin);
}
