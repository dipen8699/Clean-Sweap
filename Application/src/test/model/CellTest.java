// Test File: CellTest.java
package test.model;
import static org.junit.Assert.*;
import com.cleansweep.model.*;
import org.junit.Test;

public class CellTest {
    
    @Test
    public void testCell(){
        Cell testCell = new Cell(new Position(0,0), SurfaceType.BARE_FLOOR);

        assertEquals(new Position(0,0), testCell.getPosition());
        assertEquals(SurfaceType.BARE_FLOOR, testCell.getSurfaceType());
        assertEquals(0, testCell.getDirtUnits());
        assertEquals(false, testCell.isObstacle());
        assertEquals(false, testCell.hasStairs());
        assertEquals(false, testCell.hasChargingStation());

        testCell.setPosition(new Position(0, 1));
        assertEquals(new Position(0,1), testCell.getPosition());

        testCell.setSurfaceType(SurfaceType.LOW_PILE_CARPET);
        assertEquals(SurfaceType.LOW_PILE_CARPET, testCell.getSurfaceType());

        // Set all values to test the setters
        testCell.setDirtUnits(50);
        testCell.setObstacle(true);
        testCell.setHasStairs(true);
        testCell.setHasChargingStation(true);
        
        assertEquals(50, testCell.getDirtUnits()); 
        assertEquals(true, testCell.isObstacle());
        assertEquals(true, testCell.hasStairs());
        assertEquals(true, testCell.hasChargingStation());

        Cell testCell2 = new Cell(new Position(3,4), SurfaceType.HIGH_PILE_CARPET);

        // Set all values to test the getters
        testCell2.setDirtUnits(10);
        testCell2.setObstacle(true);
        testCell2.setHasStairs(true);
        testCell2.setHasChargingStation(false);
        
        assertEquals(10, testCell2.getDirtUnits());
        assertEquals(true, testCell2.isObstacle());
        assertEquals(true, testCell2.hasStairs());
        assertEquals(false, testCell2.hasChargingStation());
    }
}
