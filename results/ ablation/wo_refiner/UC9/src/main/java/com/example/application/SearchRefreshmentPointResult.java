package com.example.application;

import com.example.dto.RefreshmentPointDTO;
import java.util.List;

/**
 * Result object containing the list of matching refreshment points as DTOs.
 */
public class SearchRefreshmentPointResult {
    private List<RefreshmentPointDTO> points;

    public SearchRefreshmentPointResult(List<RefreshmentPointDTO> points) {
        this.points = points;
    }

    public List<RefreshmentPointDTO> getPoints() {
        return points;
    }
}