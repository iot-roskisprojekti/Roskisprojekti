package fi.roskisprojekti.application.port.out.persistence;

import fi.roskisprojekti.domain.entity.bin.Bin;
import fi.roskisprojekti.domain.entity.bin.BinId;

import java.util.List;
import java.util.Optional;

public interface BinRepository {
    List<Bin> findAllBins();
    Optional<Bin> findById(BinId id);
    void deleteById(BinId id);
    Bin save(Bin bin);
}
