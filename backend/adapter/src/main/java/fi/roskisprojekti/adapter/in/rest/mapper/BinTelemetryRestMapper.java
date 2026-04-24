package fi.roskisprojekti.adapter.in.rest.mapper;

import fi.roskisprojekti.adapter.in.rest.dto.BinTelemetryRestDto;
import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;
import org.springframework.stereotype.Component;

/**
 * Omaan päähän paljon selkeempi tää manuaalinen dto -> domain entity mappailu
 * Siitä mapstructin käytöstä tuli vaan lähinnä järkyttävä päänsärky
 * */
@Component
public class BinTelemetryRestMapper {

    public BinId toBinId(Long binId) {
        return binId != null ? new BinId(binId) : null;
    }

    public BinTelemetryRestDto toRestDto(BinTelemetry domainBinTelemetry) {
        if (domainBinTelemetry == null) return null;

        return new BinTelemetryRestDto(
                domainBinTelemetry.getBinId().value(),
                domainBinTelemetry.getMeasuredAt().value(),
                domainBinTelemetry.getDistance().value()
        );
    }
}
