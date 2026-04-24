package fi.roskisprojekti.adapter.in.telemetry.mqtt.mapper;

import fi.roskisprojekti.adapter.in.telemetry.mqtt.dto.BinTelemetryMqttDto;
import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;
import fi.roskisprojekti.domain.entity.telemetry.bin.Distance;
import org.springframework.stereotype.Component;

@Component
public class BinTelemetryMqttMapper {

    /**
     * Domain value objectit validoi meidän köykästen domain sääntöjen pohjalta, että onko mqtt:lta tuleva telemetria
     * sellasta, mitä suostutaan päästämään läpi.
     * */
    public BinTelemetry toDomainEntity(BinTelemetryMqttDto dto) {
        BinId binId = new BinId(dto.binId());
        Distance distance = new Distance(dto.distance());
        SingleTimeStamp measuredAt = new SingleTimeStamp(dto.measuredAt());

        return new BinTelemetry(binId, distance, measuredAt);
    }
}
