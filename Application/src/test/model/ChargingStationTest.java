//  Test for ChargingStation class
package test.model;
import static org.junit.Assert.*;
import com.cleansweep.model.*;
import org.junit.Test;

public class ChargingStationTest {

    @Test
    public void testChargingStation(){
        ChargingStation testChargingStation = new ChargingStation(new Position(0,0));
        assertEquals(new Position(0,0), testChargingStation.getPosition());

        testChargingStation.setPosition(new Position(0, 1));
        assertEquals(new Position(0,1), testChargingStation.getPosition());
    }

}
