package com.example.refreshmentpoint.controller;

import com.example.refreshmentpoint.model.RefreshmentPoint;
import com.example.refreshmentpoint.service.RefreshmentPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/refreshment-points")
public class RefreshmentPointController {

    @Autowired
    private RefreshmentPointService refreshmentPointService;

    /**
     * Endpoint to search for refreshment points based on type and amenities.
     * Handles potential timeouts as per quality requirements.
     *
     * @param type Optional type of refreshment point to filter by.
     * @param amenities Optional comma-separated list of amenities to filter by.
     * @return A list of matching RefreshmentPoint objects or an error response.
     */
    @GetMapping
    public ResponseEntity<?> searchRefreshmentPoints(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) List<String> amenities) {
        try {
            List<RefreshmentPoint> points = refreshmentPointService.searchRefreshmentPoints(type, amenities);
            if (points.isEmpty()) {
                return new ResponseEntity<>("No refreshment points found matching the criteria.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(points, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
        }
    }

    /**
     * Endpoint to get a refreshment point by its ID.
     *
     * @param id The ID of the refreshment point.
     * @return The RefreshmentPoint object if found, or a 404 Not Found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RefreshmentPoint> getRefreshmentPointById(@PathVariable String id) {
        RefreshmentPoint point = refreshmentPointService.getRefreshmentPointById(id);
        return Optional.ofNullable(point)
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
