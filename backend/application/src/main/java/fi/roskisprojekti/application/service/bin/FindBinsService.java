package fi.roskisprojekti.application.service.bin;

import fi.roskisprojekti.application.port.in.bin.FindBinsUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.domain.entity.bin.Bin;
import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.bin.BinStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindBinsService implements FindBinsUseCase {
    private final BinRepository binRepository;
    @Override
    public List<Bin> findAllBins() {
        return binRepository.findAllBins();
    }

    @Override
    public List<Bin> findByQuery(String query) {
        return List.of();
    }

    @Override
    public List<Bin> findByStatus(BinStatus status) {
        return List.of();
    }

    @Override
    public Bin findById(BinId id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new java.util.NoSuchElementException("No bin found with ID: " + id.value()));
    }
}
