//  Test class for Position.java
package test.model;
import static org.junit.Assert.*;
import com.cleansweep.model.*;
import org.junit.Test;

public class PositionTest {
        
        @Test
        public void testPosition(){
            Position testPosition = new Position(0,0);
            Position testPosition2 = new Position(0,0);
            Position testPosition3 = new Position(1,1);
    
            // Test getters and setters
            assertEquals(0, testPosition.getX());
            assertEquals(0, testPosition.getY());
    
            // Test setters
            testPosition.setX(1);
            testPosition.setY(1);
    
            // Test getters
            assertEquals(1, testPosition.getX());
            assertEquals(1, testPosition.getY());

            // Test equals
            assertEquals(false, testPosition.equals(testPosition2));
            assertEquals(true, testPosition.equals(testPosition3));

            // Test hashCode
            assertEquals(32, testPosition.hashCode());
        }
}
