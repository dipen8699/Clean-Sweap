package com.cleansweep.sensor;

import com.cleansweep.model.FloorPlan;
import com.cleansweep.model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObstacleSensor {
    private FloorPlan floorPlan;

    public ObstacleSensor(FloorPlan floorPlan) {
        this.floorPlan = floorPlan;
    }

    public Map<String, Boolean> detectObstacles(Position currentPosition) {
        Map<String, Boolean> obstacles = new HashMap<>();
        Position north = new Position(currentPosition.getX(), currentPosition.getY() - 1);
        obstacles.put("North", isAccessible(north));
        Position south = new Position(currentPosition.getX(), currentPosition.getY() + 1);
        obstacles.put("South", isAccessible(south));
        Position east = new Position(currentPosition.getX() + 1, currentPosition.getY());
        obstacles.put("East", isAccessible(east));
        Position west = new Position(currentPosition.getX() - 1, currentPosition.getY());
        obstacles.put("West", isAccessible(west));
        return obstacles;
    }
    private boolean isAccessible(Position position) {
        return floorPlan.isAccessible(position);
    }
}
