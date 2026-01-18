// Data Access Object for PointOfRest (simulated in-memory database)
package com.etour.agency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PointOfRestDAO {
    private static List<PointOfRest> points = new ArrayList<>();
    private static int nextId = 1;

    static {
        // Sample initial data
        points.add(new PointOfRest(nextId++, "Mountain Cafe", "Alps", "Cozy cafe with view", 50, true));
        points.add(new PointOfRest(nextId++, "Lake Rest Stop", "Lake District", "Near the lake", 30, true));
    }

    public List<PointOfRest> getAllPoints() {
        return new ArrayList<>(points);
    }

    public Optional<PointOfRest> getPointById(int id) {
        return points.stream().filter(p -> p.getId() == id).findFirst();
    }

    public boolean updatePoint(PointOfRest updatedPoint) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getId() == updatedPoint.getId()) {
                points.set(i, updatedPoint);
                return true;
            }
        }
        return false;
    }

    public void addPoint(PointOfRest point) {
        point.setId(nextId++);
        points.add(point);
    }
}