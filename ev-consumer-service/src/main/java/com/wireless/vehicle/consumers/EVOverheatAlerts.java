package com.wireless.vehicle.consumers;

import com.wireless.vehicle.model.EVTelemetry;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EVOverheatAlerts {

    @KafkaListener(
            topics = "ev-overheat-alerts",
            groupId = "overheat-alert-consumer-group"
    )
    public void consume(EVTelemetry ev) {

        System.out.println(
                "🔥 OVERHEAT ALERT 🔥 " +
                        "Vehicle=" + ev.vehicleId() +
                        " BatteryTemp=" + ev.batteryTemp() +
                        " MotorTemp=" + ev.motorTemp()
        );
    }
}