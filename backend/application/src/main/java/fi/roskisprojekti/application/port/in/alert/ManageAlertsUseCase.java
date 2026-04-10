package fi.roskisprojekti.application.port.in.alert;

import fi.roskisprojekti.domain.entity.alert.Alert;

import java.util.List;

public interface ManageAlertsUseCase {
    List<Alert> findAll();
    void save(Alert alert);
}
