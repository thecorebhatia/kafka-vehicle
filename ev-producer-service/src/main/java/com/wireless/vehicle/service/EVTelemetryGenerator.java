package com.wireless.vehicle.service;

import com.wireless.vehicle.model.EVTelemetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class EVTelemetryGenerator {

    private final KafkaTemplate<String, EVTelemetry> kafkaTemplate;
    private final Random random = new Random();
    private static final List<String> VEHICLES = IntStream.rangeClosed(100, 120).mapToObj(i -> "EV-" + i).toList();

    @Value("${electric.vehicle.topic}")
    private String evTopic;

    public EVTelemetryGenerator(KafkaTemplate<String, EVTelemetry> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendVehicleInfo() {
        String vehicleId = VEHICLES.get(random.nextInt(VEHICLES.size()));
        boolean isNumberTen = random.nextInt(10) == 0;
        int batteryPercent = isNumberTen
                ? random.nextInt(15)
                : 20 + random.nextInt(81);
        double batteryTemp = isNumberTen
                ? 65 + random.nextDouble() * 10
                : 25 + random.nextDouble() * 15;
        double motorTemp = isNumberTen
                ? 95 + random.nextDouble() * 10
                : 40 + random.nextDouble() * 30;

        EVTelemetry evTelemetry = new EVTelemetry(vehicleId,
                30 + random.nextInt(90),
                batteryPercent,
                batteryTemp,
                motorTemp,
                System.currentTimeMillis());

        kafkaTemplate.send(evTopic, vehicleId, evTelemetry);
        System.out.println("Topic : " + evTopic + "Vehicle Id : " + vehicleId + " Info : " + evTelemetry);
    }
}
