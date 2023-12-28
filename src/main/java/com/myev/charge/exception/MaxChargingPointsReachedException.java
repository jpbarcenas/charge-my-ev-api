package com.myev.charge.exception;

public class MaxChargingPointsReachedException extends RuntimeException {
    private String resourceName;
    private long stationId;
    private int maxChargingPoints;

    public MaxChargingPointsReachedException(String resourceName, long stationId, int maxChargingPoints) {
        // Charging Station with id : 1 reached the maximum charging points : 10
        super(String.format("%s with id: " + stationId + " reached the maximum charging points: %s", resourceName, maxChargingPoints));
        this.resourceName = resourceName;
        this.stationId = stationId;
        this.maxChargingPoints = maxChargingPoints;
    }

    public String getResourceName() {
        return resourceName;
    }

    public long getStationId() {
        return stationId;
    }

    public int getMaxChargingPoints() {
        return maxChargingPoints;
    }
}
