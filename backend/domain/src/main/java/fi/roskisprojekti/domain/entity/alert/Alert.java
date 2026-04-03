package fi.roskisprojekti.domain.entity.alert;

import fi.roskisprojekti.domain.entity.bin.BinId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Alert {
    private final AlertId alertId;
    private final BinId bindId;
    private final AlertState alertState;
    private final AlertType alertType;
    private final TriggeredAt triggeredAt;

}
