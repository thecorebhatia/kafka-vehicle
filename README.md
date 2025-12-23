# 🚗 Connected Vehicle Real-Time Streaming Application (Kafka + Spring Boot)

This project demonstrates a **basic but production-style connected vehicle platform**
built using **Apache Kafka, Kafka Streams, and Spring Boot**.

The system simulates electric vehicle (EV) telemetry data, processes it in real time,
and routes meaningful events (alerts, clean data) to downstream consumers using an
event-driven architecture.

---

## 🧠 Problem Statement

Modern connected vehicles continuously generate telemetry such as:
- Speed
- Battery percentage
- Battery temperature
- Motor temperature

This data must be:
- Ingested in real time
- Processed continuously
- Filtered and analyzed
- Delivered to multiple independent consumers

This project solves that using **Kafka as the backbone**.

---

## 🏗️ High-Level Architecture

```
EV Producer
   |
   v
Kafka Topic (ev-telemetry)
   |
   v
Kafka Streams Processor
   |
   +--> ev-clean-telemetry
   |
   +--> ev-overheat-alerts
   |
   +--> ev-low-battery
```

Each output topic is consumed by an independent consumer.

---

## 🔁 Data Flow Explained

1. **Producer**
   - Simulates multiple EVs
   - Publishes telemetry every **5 millisecond**
   - Uses `vehicleId` as Kafka **message key**
   - Ensures ordering per vehicle

2. **Kafka**
   - Acts as the central event backbone
   - Stores events durably
   - Partitions data by `vehicleId`
   - Enables replay and scalability

3. **Kafka Streams Processor**
   - Consumes raw telemetry
   - Applies real-time business rules:
     - Filters valid data
     - Detects overheat conditions
     - Detects low battery scenarios
   - Produces new events to dedicated topics

4. **Consumers**
   - Subscribe to specific topics
   - Act on events independently
   - Currently implemented in one service (can be split later)

---

## 📦 Project Modules

```
kafka-vehicle/
├── ev-producer-service
├── ev-stream-processor
└── ev-consumer-service
```

---

## 🧩 Kafka Topics

| Topic Name         | Purpose |
|--------------------|--------|
| ev-telemetry       | Raw telemetry |
| ev-clean-telemetry | Valid telemetry |
| ev-overheat-alerts | Overheat alerts |
| ev-low-battery     | Low battery alerts |

---

## ⚙️ Technology Stack

- Java 21
- Spring Boot
- Apache Kafka
- Kafka Streams
- Docker
- Maven

---

## ▶️ How to Run

1. Start Kafka using Docker
2. Start Stream Processor
3. Start Consumer Service
4. Start Producer Service

---

## 🎯 Kafka Concepts Demonstrated

- Topic partitioning
- Message ordering
- Consumer groups
- Kafka Streams processing
- Event-driven architecture
- Fault tolerance

---

## ✅ Conclusion

This project represents a **complete basic connected vehicle streaming platform**
and follows real-world architectural patterns used in IoT and automotive systems.
