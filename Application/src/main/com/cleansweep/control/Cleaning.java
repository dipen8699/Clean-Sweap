package com.cleansweep.control;

import com.cleansweep.sensor.SensorSimulator;
import com.cleansweep.utils.Logger;

public class Cleaning {
    private static final int DIRT_CAPACITY = 50;
    private int currentDirtCapacity;
    private SensorSimulator sensorSimulator;
    private Logger logger;

    public Cleaning(SensorSimulator sensorSimulator) {
        this.sensorSimulator = sensorSimulator;
        this.currentDirtCapacity = 0;
        this.logger = Logger.getInstance();
    }

    public void cleanCurrentPosition() {
        if (sensorSimulator.isDirtPresent()) {
            currentDirtCapacity++;
            sensorSimulator.updateDirtLevel();
            logger.logInfo("Cleaned dirt at position " + sensorSimulator.getCurrentPosition() +
                    ". Current dirt capacity: " + currentDirtCapacity + "/" + DIRT_CAPACITY);
        }
    }

    public boolean isDirtCapacityFull() {
        return currentDirtCapacity >= DIRT_CAPACITY;
    }

    public void emptyDirtContainer() {
        this.currentDirtCapacity = 0;
        logger.logInfo("Dirt container emptied. Current dirt capacity: " + currentDirtCapacity + "/" + DIRT_CAPACITY);
    }
}
