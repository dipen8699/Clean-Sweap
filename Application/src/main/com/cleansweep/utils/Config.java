package com.cleansweep.utils;
import com.cleansweep.control.Cleaning;
import com.cleansweep.control.Diagnostics;
import com.cleansweep.control.Navigation;
import com.cleansweep.control.PowerManagement;
import com.cleansweep.model.Cell;
import com.cleansweep.model.FloorPlan;
import com.cleansweep.model.Position;
import com.cleansweep.model.SurfaceType;
import com.cleansweep.sensor.SensorSimulator;
import com.cleansweep.utils.Logger;
public class Config {
    public void run() {
        Logger logger = Logger.getInstance();
        FloorPlan floorPlan = new FloorPlan();
        initializeFloorPlan(floorPlan);

        Position startPosition = new Position(0, 0);
        SensorSimulator sensorSimulator = new SensorSimulator(floorPlan, startPosition);
        Navigation navigation = new Navigation(sensorSimulator);
        Cleaning cleaning = new Cleaning(sensorSimulator);
        PowerManagement powerManagement = new PowerManagement(sensorSimulator);
        Diagnostics diagnostics = new Diagnostics(sensorSimulator);

        floorPlan.displayFloorPlan();
        diagnostics.runDiagnostics();

        logger.logInfo("Starting navigation and cleaning...");
        while (true) {
            if (sensorSimulator.isDirtPresent()) {
                cleaning.cleanCurrentPosition();
            }

            powerManagement.updatePowerUsage(sensorSimulator.getCurrentSurface());

            if (powerManagement.isLowPower()) {
                logger.logWarning("Low power detected. Returning to the nearest charging station...");
                navigation.returnToChargingStation();
                powerManagement.recharge();
                logger.logInfo("Vacuum recharged, resuming cleaning...");
            }

            boolean moved = navigation.moveNext();
            if (!moved) {
                logger.logInfo("All accessible positions have been covered.");
                break;
            }
        }

        logger.logInfo("Cleaning and navigation completed.");
    }

    private static void initializeFloorPlan(FloorPlan floorPlan) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                SurfaceType surfaceType = (x + y) % 3 == 0 ? SurfaceType.HIGH_PILE_CARPET : (x + y) % 3 == 1 ? SurfaceType.LOW_PILE_CARPET : SurfaceType.BARE_FLOOR;
                Cell cell = new Cell(new Position(x, y), surfaceType, (x + y) % 2 == 0 ? 1 : 0, false, false, false);

                if ((x == 2 && y == 2) || (x == 1 && y == 3)) {
                    cell.setObstacle(true);
                } else if (x == 3 && y == 1) {
                    cell.setHasStairs(true);
                }

                if ((x == 1 && y == 1) || (x == 4 && y == 4)) {
                    cell.setHasChargingStation(true);
                }
                if ((x + y) % 2 == 0) {
                    cell.setDirtUnits(3);
                }

                floorPlan.addCell(cell);
            }
        }
    }
}

