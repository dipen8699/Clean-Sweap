package com.cleansweep.model;


import java.util.HashMap;
import java.util.Map;

public class FloorPlan {
    private Map<Position, Cell> grid;

    public FloorPlan() {
        grid = new HashMap<>();
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

}
