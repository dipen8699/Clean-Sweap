package com.cleansweep.control;

import com.cleansweep.model.SurfaceType;
import com.cleansweep.sensor.SensorSimulator;
import com.cleansweep.utils.Logger;

public class PowerManagement {
    private static final int MAX_POWER = 250;
    private int currentPower;
    private SensorSimulator sensorSimulator;
    private Logger logger;

    public PowerManagement(SensorSimulator sensorSimulator) {
        this.sensorSimulator = sensorSimulator;
        this.currentPower = MAX_POWER;
        this.logger = Logger.getInstance();
    }

    public void updatePowerUsage(SurfaceType surfaceType) {
        int powerUsage = getPowerUsage(surfaceType);
        currentPower -= powerUsage;
        logger.logInfo("Power usage: " + powerUsage + " units. Remaining power: " + currentPower + "/" + MAX_POWER);
    }

    public boolean isLowPower() {
        return currentPower < 20;
    }

    public void recharge() {
        currentPower = MAX_POWER;
        logger.logInfo("Recharged to full power: " + MAX_POWER);
    }

    private int getPowerUsage(SurfaceType surfaceType) {
        switch (surfaceType) {
            case BARE_FLOOR:
                return 1;
            case LOW_PILE_CARPET:
                return 2;
            case HIGH_PILE_CARPET:
                return 3;
            default:
                return 1;
        }
    }
}
