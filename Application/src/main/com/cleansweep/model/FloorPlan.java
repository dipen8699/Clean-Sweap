package com.cleansweep.model;

import java.util.HashMap;
import java.util.Map;

public class FloorPlan {
    private Map<Position, Cell> grid;
    private int maxX;
    private int maxY;

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

    public void displayFloorPlanWithCurrentPosition(Position currentPosition) {
        for (Map.Entry<Position, Cell> entry : grid.entrySet()) {
            Position pos = entry.getKey();
            Cell cell = entry.getValue();
            System.out.println("Position: (" + pos.getX() + ", " + pos.getY() + "), Surface: "
                    + cell.getSurfaceType() + ", Dirt: " + cell.getDirtUnits()
                    + ", Obstacle: " + cell.isObstacle() + ", Stairs: " + cell.hasStairs()
                    + ", Charging Station: " + cell.hasChargingStation()
                    + ", North Wall: " + cell.hasNorthWall() + ", South Wall: " + cell.hasSouthWall()
                    + ", East Wall: " + cell.hasEastWall() + ", West Wall: " + cell.hasWestWall()
                    + ", Current Position: " + (pos.equals(currentPosition) ? "Yes" : "No"));
        }
        System.out.println("Grid Size : " + grid.size());
    }


    public void setGridDimensions(){
        for (Map.Entry<Position, Cell> entry : grid.entrySet()){
            Position pos = entry.getKey();
            if (pos.getX() > maxX){
                this.maxX = pos.getX();
            }
            if (pos.getY() > maxY){
                this.maxY = pos.getY();
            }
        }
    }

    public void getGridDimensions(){
        System.out.println("Grid Dimensions: " + (maxX + 1) + " x " + (maxY + 1));
    }

    public void visualizeFloorPlan(Position currentPosition) {
        System.out.println("Visualizing Floor Plan...");
        System.out.println("Current Position: " + currentPosition); 
        for (int y = 0; y < maxY+1; y++){
            for (int x = 0; x < maxX+1; x++){
                Position pos = new Position(x, y);
                Cell cell = grid.get(pos);

                if (x == 0){
                    System.out.print("|");
                }
                

                if (x == currentPosition.getX() && y == currentPosition.getY()){
                    System.out.print("CP");
                } else if (cell.hasChargingStation()){
                    System.out.print("CS");
                } else if (cell.isObstacle()){
                    System.out.print("XX");
                } else if (cell.hasStairs()){
                    System.out.print("ST");
                } else if (cell.getDirtUnits() == 0){
                    System.out.print("OO");
                } else {
                    System.out.print("  ");
                }
                if (cell.hasEastWall() || x == maxX){ 
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
            for (int i = 0; i < maxX+1; i++){
                Position pos = new Position(i, y);
                Cell cell = grid.get(pos);
                if (cell.hasSouthWall() || y == maxY){
                    System.out.print("---");
                }
                else {
                    System.out.print("   ");
                }
                
            }
            System.out.println();
        }

        System.out.println();

    }
}
