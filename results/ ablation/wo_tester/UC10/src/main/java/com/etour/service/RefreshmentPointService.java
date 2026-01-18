package com.etour.service;

import com.etour.model.RefreshmentPoint;
import com.etour.model.RefreshmentPointDetails;
import java.util.List;

/**
 * Interface for refreshment point operations and error handling (R2 Exit Condition 2)
 */
public interface RefreshmentPointService {
    RefreshmentPointDetails getPointDetails(String pointId);
    List<RefreshmentPoint> getAllRefreshmentPoints();
    void handleConnectionError();
}