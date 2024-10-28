package com.cleansweep.model;
import java.io.*;
import java.util.*;

public class FloorPlanLoader {
    public static FloorPlan loadFromFile(String filePath) {
        FloorPlan floorPlan = new FloorPlan();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                int x = Integer.parseInt(tokens[0].trim());
                int y = Integer.parseInt(tokens[1].trim());
                SurfaceType surface = SurfaceType.valueOf(tokens[2].trim().toUpperCase());
                boolean isObstacle = Boolean.parseBoolean(tokens[3].trim());
                boolean hasStairs = Boolean.parseBoolean(tokens[4].trim());
                boolean hasChargingStation = Boolean.parseBoolean(tokens[5].trim());
                int dirtUnits = Integer.parseInt(tokens[6].trim());

                Position pos = new Position(x, y);
                Cell cell = new Cell(pos, surface);
                cell.setObstacle(isObstacle);
                cell.setHasStairs(hasStairs);
                cell.setHasChargingStation(hasChargingStation);
                cell.setDirtUnits(dirtUnits);

                floorPlan.addCell(cell);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return floorPlan;
    }
}

