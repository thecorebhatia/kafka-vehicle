package com.wireless.vehicle.consumers;

import com.wireless.vehicle.model.EVTelemetry;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class EVCleanTelemetry {

    @KafkaListener(
            topics = "ev-clean-telemetry",
            groupId = "clean-telemetry-consumer-group"
    )
    public void consume(EVTelemetry ev,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                        @Header(KafkaHeaders.OFFSET) long offset) {

        System.out.println(
                "[CLEAN] Partition=" + partition +
                        " Offset=" + offset +
                        " Vehicle=" + ev.vehicleId() +
                        " Speed=" + ev.speed() +
                        " Battery=" + ev.batteryPercent()
        );
    }
}