package com.cleansweep.sensor;

import com.cleansweep.model.FloorPlan;
import com.cleansweep.model.Position;
import com.cleansweep.model.SurfaceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SensorSimulator {
    private FloorPlan floorPlan;
    private Position currentPosition;

    private DirtSensor dirtSensor;
    private SurfaceSensor surfaceSensor;
    private ObstacleSensor obstacleSensor;
    private ChargingStationSensor chargingStationSensor;

    public SensorSimulator(FloorPlan floorPlan, Position startPosition) {
        this.floorPlan = floorPlan;
        this.currentPosition = startPosition;
        this.dirtSensor = new DirtSensor();
        this.surfaceSensor = new SurfaceSensor();
        this.obstacleSensor = new ObstacleSensor(floorPlan);
        this.chargingStationSensor = new ChargingStationSensor(floorPlan);
    }

    public void updatePosition(Position newPosition) {
        this.currentPosition = newPosition;
    }

    public boolean isDirtPresent() {
        return dirtSensor.isDirtPresent(currentPosition, floorPlan);
    }

    public void updateDirtLevel() {
        if (isDirtPresent()) {
            floorPlan.getCell(currentPosition).decreaseDirtUnits(1);
        }
    }

    public SurfaceType getCurrentSurface() {
        return surfaceSensor.detectSurfaceType(currentPosition, floorPlan);
    }

//    public Map<String, Boolean> getObstacleSensors() {
//        return obstacleSensor.detectObstacles(currentPosition);
//    }

    public boolean isChargingStationNearby() {
        return chargingStationSensor.isChargingStationNearby(currentPosition);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }
        public List<String> detectOpenDirections(Position currentPosition) {
        List<String> openDirections = new ArrayList<>();

        Position north = new Position(currentPosition.getX(), currentPosition.getY() - 1);
        if (isAccessible(north) && !floorPlan.getCell(currentPosition).hasNorthWall()) {
            openDirections.add("North");
        }

        Position south = new Position(currentPosition.getX(), currentPosition.getY() + 1);
        if (isAccessible(south) && !floorPlan.getCell(currentPosition).hasSouthWall()) {
            openDirections.add("South");
        }

        Position east = new Position(currentPosition.getX() + 1, currentPosition.getY());
        if (isAccessible(east) && !floorPlan.getCell(currentPosition).hasEastWall()) {
            openDirections.add("East");
        }

        Position west = new Position(currentPosition.getX() - 1, currentPosition.getY());
        if (isAccessible(west) && !floorPlan.getCell(currentPosition).hasWestWall()) {
            openDirections.add("West");
        }
        return openDirections;
    }
    private boolean isAccessible(Position position) {
        return floorPlan.isAccessible(position);
    }
}
