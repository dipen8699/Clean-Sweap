package control;
import com.cleansweep.control.*;
import com.cleansweep.model.*;
import com.cleansweep.sensor.SensorSimulator;
import com.cleansweep.utils.*;

public class PowerSurfaceTest {

    public static void main(String[] args) {

        Logger logger = Logger.getInstance();
        FloorPlan floorPlan = new FloorPlan();
        DirtCapFloorplan(floorPlan);
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
                navigation.returnToChargingStation();
                powerManagement.recharge();
            }

            if (cleaning.isDirtCapacityFull())
            {
                logger.logInfo("Dirt capacity is full. Please Empty the Dirt Container!");
                break;
            }

           boolean moved = navigation.moveNext();
            if (!moved) {
                logger.logInfo("All accessible positions have been covered.");
                navigation.returnToChargingStation();
                logger.logInfo("Cleaning and navigation completed.");
                break;
            }
        }
    }
    

    public void setUp() {
        
    }


    private static void DirtCapFloorplan(FloorPlan floorPlan){
        for(int x = 0; x < 1; x++){
            for(int y = 0; y < 3; y++){
                SurfaceType surfaceType = (x + y) % 3 == 0 ? SurfaceType.HIGH_PILE_CARPET : (x + y) % 3 == 1 ? SurfaceType.LOW_PILE_CARPET : SurfaceType.BARE_FLOOR;
                Cell cell = new Cell(new Position(x, y), surfaceType, (x + y) % 2 == 0 ? 1 : 0, false, false, false, false, false, false, false);
                if((x==0 && y== 0 )){
                    cell.setHasChargingStation(true);
                }
                if ((x + y) % 3 == 0) {
                    cell.setDirtUnits(3);
                }
                floorPlan.addCell(cell);
            }
        }
    }
}