package com.cleansweep.model;

import java.util.HashMap;
import java.util.Map;

public class FloorPlan {
    private Map<Position, Cell> grid;

    public FloorPlan() {
        this.grid = new HashMap<>();
    }

    public void addCell(Cell cell) {
        grid.put(cell.getPosition(), cell);
    }

    public Cell getCell(Position position) {
        return grid.get(position);
    }

    public boolean isAccessible(Position position) {
        Cell cell = grid.get(position);
        return cell != null && !cell.isObstacle() && !cell.hasStairs();
    }

    public Map<Position, Cell> getGrid() {
        return grid;
    }

    public Map<Position, Cell> getChargingStations() {
        Map<Position, Cell> chargingStations = new HashMap<>();
        for (Map.Entry<Position, Cell> entry : grid.entrySet()) {
            if (entry.getValue().hasChargingStation()) {
                chargingStations.put(entry.getKey(), entry.getValue());
            }
        }
        return chargingStations;
    }

    public void displayFloorPlan() {
        for (Map.Entry<Position, Cell> entry : grid.entrySet()) {
            Position pos = entry.getKey();
            Cell cell = entry.getValue();
            System.out.println("Position: (" + pos.getX() + ", " + pos.getY() + "), Surface: "
                    + cell.getSurfaceType() + ", Dirt: " + cell.getDirtUnits()
                    + ", Obstacle: " + cell.isObstacle() + ", Stairs: " + cell.hasStairs()
                    + ", Charging Station: " + cell.hasChargingStation()
                    + ", North Wall: " + cell.hasNorthWall() + ", South Wall: " + cell.hasSouthWall()
                    + ", East Wall: " + cell.hasEastWall() + ", West Wall: " + cell.hasWestWall());
        }
        System.out.println("Grid Size : " + grid.size());
    }
}
