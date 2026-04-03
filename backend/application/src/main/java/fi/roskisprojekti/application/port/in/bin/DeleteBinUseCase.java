package fi.roskisprojekti.application.port.in.bin;

import fi.roskisprojekti.domain.entity.bin.BinId;

public interface DeleteBinUseCase {
    void deleteBinById(BinId id);
}
