package fi.roskisprojekti.application.port.out.persistence;

import fi.roskisprojekti.domain.entity.alert.Alert;
import fi.roskisprojekti.domain.entity.alert.AlertState;
import fi.roskisprojekti.domain.entity.bin.BinId;

import java.util.List;

public interface AlertRepository {
    List<Alert> findAll();
    Alert save(Alert alert);
    boolean existsByBinIdAndAlertState(BinId binId, AlertState state);
}
