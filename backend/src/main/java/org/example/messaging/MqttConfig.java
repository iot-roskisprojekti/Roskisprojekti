package org.example.messaging;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.example.service.MeasurementService;
import org.example.adapter.EmailAdapter;
import org.example.model.dto.MeasurementDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.adapter.EmailAdapter;

@Configuration
public class MqttConfig {
    private static final Logger log = LoggerFactory.getLogger(MqttConfig.class);

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[] { brokerUrl });
        options.setCleanSession(true);
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("backendClient", mqttClientFactory(), "telemetry/bins/#");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler(MeasurementService measurementService, ObjectMapper objectMapper, EmailAdapter emailAdapter) {
        return message -> {
            try {
                String payload = (String) message.getPayload();
                log.info("DEBUG: Raw MQTT Payload -> {}", payload);

                // Muutetaan JSON-viesti DTO-olioksi
                MeasurementDto dto = objectMapper.readValue(payload, MeasurementDto.class);

                // 1. Tallennetaan mittaus tietokantaan
                measurementService.processTelemetry(dto);

                // 2. AUTOMAATIO: Tarkistetaan täyttöaste (käytetään fillPercent() record-metodia)
                if (dto.fillPercent() != null && dto.fillPercent() >= 90.0) {
                    log.warn("HÄLYTYS: Roskis {} on täyttymässä ({}%)!", dto.siteId(), dto.fillPercent());
                    
                    emailAdapter.sendAlert(
                        "jorkki25@gmail.com", // <-- MUISTA VAIHTAA TÄMÄ!
                        "Roskisilmoitus: Astia " + dto.siteId() + " on täynnä",
                        "Huomio! Roska-astian " + dto.siteId() + " täyttöaste on " + dto.fillPercent() + "%. " +
                        "Käy tyhjentämässä astia mahdollisimman pian."
                    );
                    log.info("Sähköpostihälytys lähetetty onnistuneesti!");
                }

            } catch (Exception e) {
                log.error("VIRHE: Viestin käsittely epäonnistui. Payload: {}", message.getPayload(), e);
            }
        };
    }
}