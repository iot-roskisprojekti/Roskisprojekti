package fi.roskisprojekti.application.port.in.bin;

import fi.roskisprojekti.domain.bin.BinId;

public interface DeleteBinUseCase {
    void deleteBinById(BinId id);
}
