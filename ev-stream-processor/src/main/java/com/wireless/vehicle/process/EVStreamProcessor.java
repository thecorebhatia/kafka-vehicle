package com.wireless.vehicle.process;

import com.wireless.vehicle.model.EVTelemetry;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EVStreamProcessor {

    @Value("${electric.vehicle.topic}")
    private String topic;

    @Bean
    public KStream<String, EVTelemetry> evStream(StreamsBuilder builder) {

        KStream<String, EVTelemetry> stream =
                builder.stream(topic);

        // Log stream events (for verification)
        stream.peek((key, value) ->
                System.out.println(
                        "STREAM | Vehicle=" + key +
                                " Speed=" + value.speed() +
                                " Battery=" + value.batteryPercent()
                )
        );

        // Filter valid telemetry
        stream
                .filter((key, value) ->
                        value.batteryPercent() >= 0 &&
                                value.batteryPercent() <= 100
                )
                .to("ev-clean-telemetry");

        // Overheat alerts
        stream
                .filter((key, value) ->
                        value.batteryTemp() > 60 ||
                                value.motorTemp() > 90
                )
                .to("ev-overheat-alerts");

        // Low battery alerts
        stream
                .filter((key, value) ->
                        value.batteryPercent() < 20
                )
                .to("ev-low-battery");

        return stream;
    }
}
