package com.example.refreshmentpoint.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshmentPoint {
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private String type; // e.g., 'restaurant', 'cafe', 'park'
    private List<String> amenities; // e.g., 'wifi', 'restroom', 'parking'
}
