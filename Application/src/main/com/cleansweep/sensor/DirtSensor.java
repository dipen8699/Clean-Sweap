package com.cleansweep.sensor;

import com.cleansweep.model.FloorPlan;
import com.cleansweep.model.Position;

public class DirtSensor {
    public boolean isDirtPresent(Position position, FloorPlan floorPlan) {
        return floorPlan.getCell(position).getDirtUnits() > 0;
    }
}

