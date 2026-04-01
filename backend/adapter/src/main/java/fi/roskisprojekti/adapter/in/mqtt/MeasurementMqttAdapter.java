package fi.roskisprojekti.adapter.in.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.roskisprojekti.adapter.in.mqtt.dto.MeasurementMqttDto;
import fi.roskisprojekti.application.port.in.telemetry.ProcessTelemetryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class MeasurementMqttAdapter {
    private final ProcessTelemetryUseCase processTelemetryUseCase;
    private final ObjectMapper objectMapper;

    public MeasurementMqttAdapter(ProcessTelemetryUseCase processTelemetryUseCase, ObjectMapper objectMapper) {
        this.processTelemetryUseCase = processTelemetryUseCase;
        this.objectMapper = objectMapper;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler telemetryHandler() {
        return message -> {
            try {
                String payload = (String) message.getPayload();

                MeasurementMqttDto dto = objectMapper.readValue(payload, MeasurementMqttDto.class);

                processTelemetryUseCase.processTelemetry(dto.binId(), dto.distance(), dto.measuredAt());

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
