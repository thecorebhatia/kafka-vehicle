package com.wireless.vehicle.consumers;

import com.wireless.vehicle.model.EVTelemetry;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EVLowBattery {

    @KafkaListener(
            topics = "ev-low-battery",
            groupId = "low-battery-consumer-group"
    )
    public void consume(EVTelemetry ev) {

        System.out.println(
                "🔋 LOW BATTERY 🔋 " +
                        "Vehicle=" + ev.vehicleId() +
                        " Battery=" + ev.batteryPercent()
        );
    }
}
