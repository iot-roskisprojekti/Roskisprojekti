package fi.roskisprojekti.application.port.in.bin;

import fi.roskisprojekti.domain.bin.Bin;

public interface CreateBinUseCase {
    Bin createBin(Bin bin);
}
