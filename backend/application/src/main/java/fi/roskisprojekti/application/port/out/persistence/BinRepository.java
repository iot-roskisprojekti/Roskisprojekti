package fi.roskisprojekti.application.port.out.persistence;

import fi.roskisprojekti.domain.bin.Bin;
import fi.roskisprojekti.domain.bin.BinId;

import java.util.List;
import java.util.Optional;

public interface BinRepository {
    List<Bin> findAllBins();
    Optional<Bin> findById(BinId id);
    void save(Bin bin);
}
