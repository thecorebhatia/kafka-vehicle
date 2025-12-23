package com.wireless.vehicle.model;

import java.util.Objects;

public record EVTelemetry(
   String vehicleId,
   int speed,
   int batteryPercent,
   double batteryTemp,
   double motorTemp,
   long timestamp
) {
    @Override
    public String vehicleId() {
        return vehicleId;
    }

    @Override
    public int speed() {
        return speed;
    }

    @Override
    public int batteryPercent() {
        return batteryPercent;
    }

    @Override
    public double batteryTemp() {
        return batteryTemp;
    }

    @Override
    public double motorTemp() {
        return motorTemp;
    }

    @Override
    public long timestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EVTelemetry that = (EVTelemetry) o;
        return speed == that.speed && timestamp == that.timestamp && Double.compare(motorTemp, that.motorTemp) == 0 && batteryPercent == that.batteryPercent && Double.compare(batteryTemp, that.batteryTemp) == 0 && Objects.equals(vehicleId, that.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, speed, batteryPercent, batteryTemp, motorTemp, timestamp);
    }

    @Override
    public String toString() {
        return "EVTelemetry{" +
                "vehicleId='" + vehicleId + '\'' +
                ", speed=" + speed +
                ", batteryPercent=" + batteryPercent +
                ", batteryTemp=" + batteryTemp +
                ", motorTemp=" + motorTemp +
                ", timestamp=" + timestamp +
                '}';
    }
}
