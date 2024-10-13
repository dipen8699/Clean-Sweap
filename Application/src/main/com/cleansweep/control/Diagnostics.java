package com.cleansweep.control;

import com.cleansweep.model.Position;
import com.cleansweep.sensor.SensorSimulator;
import com.cleansweep.utils.Logger;

public class Diagnostics {
    private SensorSimulator sensorSimulator;
    private Logger logger;

    public Diagnostics(SensorSimulator sensorSimulator) {
        this.sensorSimulator = sensorSimulator;
        this.logger = Logger.getInstance();
    }

    public void runDiagnostics() {
//        Position currentPosition = sensorSimulator.getCurrentPosition();
        logger.logInfo("Running diagnostics...");
        if (sensorSimulator.isChargingStationNearby()) {
            logger.logInfo("Charging station is nearby at.");
        } else {
            logger.logWarning("No charging station detected nearby.");
        }
        logger.logInfo("Diagnostics completed.");
    }
}
