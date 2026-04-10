package fi.roskisprojekti.domain.entity.alert;

import fi.roskisprojekti.domain.common.EndTimeStamp;
import fi.roskisprojekti.domain.common.StartTimeStamp;
import fi.roskisprojekti.domain.entity.bin.BinId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Alert {
    private final AlertId alertId;
    private final BinId binId;
    private final AlertState alertState;
    private final AlertType alertType;
    private final StartTimeStamp triggeredAt;
    private final EndTimeStamp closedAt;

    public static Alert createNew(BinId binId, AlertType alertType) {
        return new Alert(
                null, // ID is unknown until save
                binId,
                AlertState.OPEN, // Default state
                alertType,
                StartTimeStamp.now(),
                null
        );
    }
}
