package fi.roskisprojekti.application.port.in.bin;

import fi.roskisprojekti.domain.bin.Bin;
import fi.roskisprojekti.domain.bin.BinId;
import fi.roskisprojekti.domain.bin.BinStatus;

import java.util.List;

public interface FindBinsUseCase {
    List<Bin> findAllBins();
    List<Bin> findByQuery(String query);
    List<Bin> findByStatus(BinStatus status);
    Bin findById(BinId id);

}
