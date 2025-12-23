package com.wireless.vehicle.scheduler;

import com.wireless.vehicle.service.EVTelemetryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GenerateVehicleInfo {

    @Autowired
    EVTelemetryGenerator generator;

    @Scheduled(fixedDelay = 5)
    public void generate() {
        generator.sendVehicleInfo();
    }

}
