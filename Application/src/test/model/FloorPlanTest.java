//  Test class for FloorPlan.java
package test.model;
import static org.junit.Assert.*;
import com.cleansweep.model.*;
import org.junit.Test;

public class FloorPlanTest {
    
        @Test
        public void testFloorPlan(){
            FloorPlan testFloorPlan = new FloorPlan();

            // Test adding a cell to the floor plan
            Cell testCell = new Cell(new Position(0,0), SurfaceType.BARE_FLOOR);
            testFloorPlan.addCell(testCell);
            assertEquals(testCell, testFloorPlan.getCell(new Position(0,0)));
            assertEquals(true, testFloorPlan.isAccessible(new Position(0,0)));
            assertEquals(false, testFloorPlan.isAccessible(new Position(1,1)));

            // Test adding a cell with an obstacle and stairs
            Cell testCell2 = new Cell(new Position(1,1), SurfaceType.BARE_FLOOR);
            testCell2.setObstacle(true);
            testFloorPlan.addCell(testCell2);
            assertEquals(false, testFloorPlan.isAccessible(new Position(1,1)));

            Cell testCell3 = new Cell(new Position(0,1), SurfaceType.BARE_FLOOR);
            testCell3.setHasStairs(true);
            testFloorPlan.addCell(testCell3);
            assertEquals(false, testFloorPlan.isAccessible(new Position(0,1)));

        }
}
