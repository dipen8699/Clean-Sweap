package com.cleansweep.model;

public class Cell {
    private Position position;
    private SurfaceType surfaceType;
    private int dirtUnits;
    private boolean isObstacle;
    private boolean hasStairs;
    private boolean hasChargingStation;
    private ChargingStation stationPosition;

    public Cell(Position position, SurfaceType surfaceType) {
        this.position = position;
        this.surfaceType = surfaceType;
        this.dirtUnits = 0;
        this.isObstacle = false;
        this.hasStairs = false;
        this.hasChargingStation = false;
    }

    public Cell(Position position, SurfaceType surfaceType, int dirtUnits, boolean isObstacle, boolean hasStairs, boolean hasChargingStation) {
        this.position = position;
        this.surfaceType = surfaceType;
        this.dirtUnits = 0;
        this.isObstacle = false;
        this.hasStairs = false;
        this.hasChargingStation = false;
    }


    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }

    public SurfaceType getSurfaceType() { return surfaceType; }
    public void setSurfaceType(SurfaceType surfaceType) { this.surfaceType = surfaceType; }

    public int getDirtUnits() { return dirtUnits; }
    public void setDirtUnits(int dirtUnits) { this.dirtUnits = dirtUnits; }

    public boolean isObstacle() { return isObstacle; }
    public void setObstacle(boolean isObstacle) { this.isObstacle = isObstacle; }

    public boolean hasStairs() { return hasStairs; }
    public void setHasStairs(boolean hasStairs) { this.hasStairs = hasStairs; }

    public boolean hasChargingStation() { return hasChargingStation; }

    public void decreaseDirtUnits(int amount) {
        if (dirtUnits > 0) {
            dirtUnits = Math.max(dirtUnits - amount, 0);
        }
    }

    public void setHasChargingStation(boolean hasChargingStation) { this.hasChargingStation = hasChargingStation; }
}