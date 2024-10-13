package com.cleansweep.sensor;

import com.cleansweep.model.FloorPlan;
import com.cleansweep.model.Position;
import com.cleansweep.model.SurfaceType;

public class SurfaceSensor {
    public SurfaceType detectSurfaceType(Position position, FloorPlan floorPlan) {
        return floorPlan.getCell(position).getSurfaceType();
    }
}

