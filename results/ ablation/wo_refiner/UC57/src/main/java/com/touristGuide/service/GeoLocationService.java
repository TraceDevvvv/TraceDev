package com.touristGuide.service;

import com.touristGuide.model.Location;
import com.touristGuide.model.Site;
import java.util.ArrayList;
import java.util.List;

public class GeoLocationService {
    /**
     * Returns sites within given radius (in km).
     */
    public List<Site> getSitesWithinRadius(Location center, float radius) {
        List<Site> result = new ArrayList<>();
        // This would normally query a spatial database; here we simulate.
        // Assume a global list of sites is available via some repository.
        // For simplicity, we return empty list; actual implementation would depend on external data.
        return result;
    }
}