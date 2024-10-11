//  Test File: SurfaceTypeTest.java
package test.model;
import static org.junit.Assert.*;
import com.cleansweep.model.*;
import org.junit.Test;


public class SurfaceTypeTest {
        
        @Test
        public void testSurfaceType(){
            SurfaceType testSurfaceType = SurfaceType.BARE_FLOOR;
            SurfaceType testSurfaceType2 = SurfaceType.LOW_PILE_CARPET;
            SurfaceType testSurfaceType3 = SurfaceType.HIGH_PILE_CARPET;
    
            // Test getPowerCost
            assertEquals(1, testSurfaceType.getPowerCost());
            assertEquals(2, testSurfaceType2.getPowerCost());
            assertEquals(3, testSurfaceType3.getPowerCost());
        }
}
