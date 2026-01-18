package com.example.application.interfaces;

/**
 * Service interface for location-related operations.
 */
public interface ILocationService {
    String getCurrentLocation(String userId);
    double calculateDistance(String location1, String location2);
}