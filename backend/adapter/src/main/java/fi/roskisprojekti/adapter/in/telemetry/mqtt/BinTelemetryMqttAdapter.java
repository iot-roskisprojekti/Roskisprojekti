package fi.roskisprojekti.adapter.in.telemetry.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.roskisprojekti.adapter.in.telemetry.mqtt.dto.BinTelemetryMqttDto;
import fi.roskisprojekti.adapter.in.telemetry.mqtt.mapper.BinTelemetryMqttMapper;
import fi.roskisprojekti.application.port.in.telemetry.bin.HandleInBoundTelemetryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BinTelemetryMqttAdapter {
    private final HandleInBoundTelemetryUseCase handleInBoundTelemetryUseCase;
    private final BinTelemetryMqttMapper binTelemetryMqttMapper;
    private final ObjectMapper objectMapper;

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handleBinTelemetry() {
        return message -> {
            try {
                String payload = (String) message.getPayload();

                BinTelemetryMqttDto dto = objectMapper.readValue(payload, BinTelemetryMqttDto.class);


                handleInBoundTelemetryUseCase.handleBinTelemetry(binTelemetryMqttMapper.toDomainEntity(dto));

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
