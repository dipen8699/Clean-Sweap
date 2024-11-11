package com.cleansweep.control;

import com.cleansweep.model.Position;
import com.cleansweep.sensor.SensorSimulator;
import com.cleansweep.utils.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Navigation {
    private SensorSimulator sensorSimulator;
    private Logger logger;
    private Set<Position> visitedPositions;
    private Stack<Position> backtrackStack;
    private Stack<Position> chargingStationPath;

    public Navigation(SensorSimulator sensorSimulator) {
        this.sensorSimulator = sensorSimulator;
        this.logger = Logger.getInstance();
        this.visitedPositions = new HashSet<>();
        this.backtrackStack = new Stack<>();
        this.chargingStationPath = new Stack<>();
        visitedPositions.add(sensorSimulator.getCurrentPosition());
    }

    public boolean moveNext() {
        Position currentPosition = sensorSimulator.getCurrentPosition();

        List<String> openDirections = sensorSimulator.detectOpenDirections(currentPosition);

        for (String direction : openDirections) {
            Position newPosition = calculateNewPosition(direction);
            if (!visitedPositions.contains(newPosition)) {
                sensorSimulator.updatePosition(newPosition);
                visitedPositions.add(newPosition);
                backtrackStack.push(currentPosition);
                chargingStationPath.push(currentPosition);
                logger.logInfo("Available directions: " + openDirections);
                logger.logInfo("Moved [" + direction + "] to position " + newPosition);
                return true;
            }
        }

        if (!backtrackStack.isEmpty()) {
            Position previousPosition = backtrackStack.pop();
            sensorSimulator.updatePosition(previousPosition);
            logger.logInfo("Backtracked to position " + previousPosition);
            return true;
        }

        logger.logWarning("No available direction to move.");
        return false;
    }

    public void returnToChargingStation() {
        Position lastPosition = sensorSimulator.getCurrentPosition();
        logger.logInfo("Navigating to the charging station...");
        while (!sensorSimulator.isChargingStationNearby()) {
            if(!chargingStationPath.isEmpty()) {
                Position previousPosition = chargingStationPath.pop();
                sensorSimulator.updatePosition(previousPosition);
                logger.logInfo("Moved to position " + previousPosition);
            } else
            if (!moveNext()) {
                logger.logWarning("Unable to reach the charging station.");
                break;
            }
        }
        logger.logInfo("Reached the charging station.");
        logger.logInfo("Returning to the last position...");
        sensorSimulator.updatePosition(lastPosition);
    }

    private Position calculateNewPosition(String direction) {
        Position currentPosition = sensorSimulator.getCurrentPosition();
        switch (direction) {
            case "North":
                return new Position(currentPosition.getX(), currentPosition.getY() - 1);
            case "South":
                return new Position(currentPosition.getX(), currentPosition.getY() + 1);
            case "East":
                return new Position(currentPosition.getX() + 1, currentPosition.getY());
            case "West":
                return new Position(currentPosition.getX() - 1, currentPosition.getY());
            default:
                return currentPosition;
        }
    }
}
