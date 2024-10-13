package com.cleansweep.sensor;

import com.cleansweep.model.Cell;
import com.cleansweep.model.FloorPlan;
import com.cleansweep.model.Position;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ChargingStationSensor {
    private FloorPlan floorPlan;

    public ChargingStationSensor(FloorPlan floorPlan) {
        this.floorPlan = floorPlan;
    }

    public boolean isChargingStationNearby(Position position) {
        List<Position> chargingStations = getChargingStations();
        for (Position station : chargingStations) {
            if (getDistance(position, station) <= 2) {
                return true;
            }
        }
        return false;
    }

    private List<Position> getChargingStations() {
        Map<Position, Cell> chargingStationsMap = floorPlan.getChargingStations();
        return new ArrayList<>(chargingStationsMap.keySet());
    }

    private int getDistance(Position a, Position b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
