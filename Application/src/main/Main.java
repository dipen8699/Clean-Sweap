//import com.cleansweep.control.Cleaning;
//import com.cleansweep.control.Diagnostics;
//import com.cleansweep.control.Navigation;
//import com.cleansweep.control.PowerManagement;
//import com.cleansweep.model.Cell;
//import com.cleansweep.model.FloorPlan;
//import com.cleansweep.model.Position;
//import com.cleansweep.model.SurfaceType;
//import com.cleansweep.sensor.SensorSimulator;
//import com.cleansweep.utils.Config;
//import com.cleansweep.utils.Logger;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
////        Config cnf = new Config();
////        cnf.run();
////        Scanner scanner = new Scanner(System.in);
//
//
//
//        Logger logger = Logger.getInstance();
//        FloorPlan floorPlan = new FloorPlan();
//        initializeFloorPlan(floorPlan);
//        Position startPosition = new Position(0, 0);
//        SensorSimulator sensorSimulator = new SensorSimulator(floorPlan, startPosition);
//        Navigation navigation = new Navigation(sensorSimulator);
//        Cleaning cleaning = new Cleaning(sensorSimulator);
//        PowerManagement powerManagement = new PowerManagement(sensorSimulator);
//        Diagnostics diagnostics = new Diagnostics(sensorSimulator);
//
//        floorPlan.displayFloorPlan();
//        diagnostics.runDiagnostics();
//
//        logger.logInfo("Starting navigation and cleaning...");
//        while (true) {
//            if (sensorSimulator.isDirtPresent()) {
//                cleaning.cleanCurrentPosition();
//            }
//            powerManagement.updatePowerUsage(sensorSimulator.getCurrentSurface());
//            if (powerManagement.isLowPower()) {
//                navigation.returnToChargingStation();
//                powerManagement.recharge();
//            }
//
//            boolean moved = navigation.moveNext();
//            if (!moved) {
//                logger.logInfo("All accessible positions have been covered.");
//                break;
//            }
//        }
//
//        logger.logInfo("Cleaning and navigation completed.");
//    }
//
//    private static void initializeFloorPlan(FloorPlan floorPlan) {
//        for (int x = 0; x < 5; x++) {
//            for (int y = 0; y < 5; y++) {
//                SurfaceType surfaceType = (x + y) % 3 == 0 ? SurfaceType.HIGH_PILE_CARPET : (x + y) % 3 == 1 ? SurfaceType.LOW_PILE_CARPET : SurfaceType.BARE_FLOOR;
//                Cell cell = new Cell(new Position(x, y), surfaceType, (x + y) % 2 == 0 ? 1 : 0, false, false, false);
//                if ((x == 2 && y == 2) || (x == 1 && y == 3)) {
//                    cell.setObstacle(true);
//                } else if (x == 3 && y == 1) {
//                    cell.setHasStairs(true);
//                }
//                if (x == 1 && y == 1) {
//                    cell.setHasChargingStation(true);
//                }
//                if ((x + y) % 2 == 0) {
//                    cell.setDirtUnits(3);
//                }
//                floorPlan.addCell(cell);
//            }
//        }
//    }
//}
import java.util.Map;
import java.util.Scanner;

import com.cleansweep.control.Cleaning;
import com.cleansweep.control.Diagnostics;
import com.cleansweep.control.Navigation;
import com.cleansweep.control.PowerManagement;
import com.cleansweep.model.Cell;
import com.cleansweep.model.FloorPlan;
import com.cleansweep.model.FloorPlanLoader;
import com.cleansweep.model.Position;
import com.cleansweep.sensor.SensorSimulator;
import com.cleansweep.utils.Logger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Logger logger = Logger.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path for the floor plan: ");

        String floorPlanFile = scanner.nextLine();
        String filePath = floorPlanFile;//"Application/src/floorplan.txt";
        System.out.println(filePath);
        FloorPlan floorPlan = FloorPlanLoader.loadFromFile(filePath);

        // System.out.println("Enter the starting position (x, y): ");
        // int x = scanner.nextInt();
        // int y = scanner.nextInt();
        Map<Position, Cell> chargingStations = floorPlan.getChargingStations();
        Position startPosition = chargingStations.keySet().iterator().next();
        
        SensorSimulator sensorSimulator = new SensorSimulator(floorPlan, startPosition);
        Navigation navigation = new Navigation(sensorSimulator);
        Cleaning cleaning = new Cleaning(sensorSimulator);
        PowerManagement powerManagement = new PowerManagement(sensorSimulator);
        Diagnostics diagnostics = new Diagnostics(sensorSimulator);

        floorPlan.displayFloorPlanWithCurrentPosition(startPosition);
        Thread.sleep(2000);
        floorPlan.setGridDimensions();
        floorPlan.getGridDimensions();
        floorPlan.visualizeFloorPlan(startPosition);
        Thread.sleep(2000);
        diagnostics.runDiagnostics();

        logger.logInfo("Starting navigation and cleaning...");
        while (true) {
            Position currentPosition = sensorSimulator.getCurrentPosition();
            if (sensorSimulator.isDirtPresent()) {
                cleaning.cleanCurrentPosition();
            }
            powerManagement.updatePowerUsage(sensorSimulator.getCurrentSurface());
            if (powerManagement.isLowPower()) {
                navigation.returnToChargingStation();
                powerManagement.recharge();
            }

            floorPlan.visualizeFloorPlan(currentPosition);

            if (cleaning.isDirtCapacityFull())
            {
                logger.logInfo("Dirt capacity is full. Please Empty the Dirt Container!");
                logger.logInfo("All accessible positions have been covered.");
                logger.logInfo("Totals" 
                        + "\t\nPower Used: " + powerManagement.getTotalPowerUsed()
                        + "\t\nTiles Cleaned: " + cleaning.getTotalCleanTiles()
                        + "\t\nDirt Collected: " + cleaning.getTotalDirtCollected());
                break;
            }

            Thread.sleep(1250);
            boolean moved = navigation.moveNext();
            if (!moved) {
                logger.logInfo("All accessible positions have been covered.");
                logger.logInfo("Totals" 
                        + "\t\nPower Used: " + powerManagement.getTotalPowerUsed()
                        + "\t\nTiles Cleaned: " + cleaning.getTotalCleanTiles()
                        + "\t\nDirt Collected: " + cleaning.getTotalDirtCollected());
                logger.logInfo("Returning to charging station...");
                break;
            }
            
        }

        logger.logInfo("Cleaning and navigation completed.");

        floorPlan.visualizeFloorPlan(sensorSimulator.getCurrentPosition());
    }
}
