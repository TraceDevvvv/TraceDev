package com.example.repository;

import com.example.model.Delay;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Repository class for managing Delay entities.
 * Simulated in-memory storage for demonstration.
 */
public class DelayRepository {
    private List<Delay> delays = new ArrayList<>();

    public void saveDelay(Delay delayData) {
        delays.add(delayData);
        // Simulating success - in real implementation would return status
    }

    public List<Delay> findByClassIdAndDate(String classId, Date date) {
        List<Delay> result = new ArrayList<>();
        for (Delay delay : delays) {
            if (delay.getClassId().equals(classId) && delay.getDate().equals(date)) {
                result.add(delay);
            }
        }
        return result;
    }
}