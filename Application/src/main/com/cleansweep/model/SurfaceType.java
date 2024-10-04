package com.cleansweep.model;

public enum SurfaceType {
    BARE_FLOOR(1),
    LOW_PILE_CARPET(2),
    HIGH_PILE_CARPET(3);

    private final int powerCost;

    SurfaceType(int powerCost) {
        this.powerCost = powerCost;
    }

    public int getPowerCost() {
        return powerCost;
    }
}
