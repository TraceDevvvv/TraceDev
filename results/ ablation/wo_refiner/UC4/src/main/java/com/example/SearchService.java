package com.example;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private CulturalObjectRepository culturalObjectRepository;
    private AuthService authService;
    private PerformanceMonitor performanceMonitor;
    private int maxConcurrentUsers = 1000;
    private int currentConcurrentUsers = 0;

    public SearchService() {
        this.culturalObjectRepository = new CulturalObjectRepositoryImpl();
        this.authService = new AuthService();
        this.performanceMonitor = new PerformanceMonitor();
    }

    public SearchService(CulturalObjectRepository culturalObjectRepository, 
                        AuthService authService, 
                        PerformanceMonitor performanceMonitor) {
        this.culturalObjectRepository = culturalObjectRepository;
        this.authService = authService;
        this.performanceMonitor = performanceMonitor;
    }

    public CulturalObjectRepository getCulturalObjectRepository() {
        return culturalObjectRepository;
    }

    public void setCulturalObjectRepository(CulturalObjectRepository culturalObjectRepository) {
        this.culturalObjectRepository = culturalObjectRepository;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public PerformanceMonitor getPerformanceMonitor() {
        return performanceMonitor;
    }

    public void setPerformanceMonitor(PerformanceMonitor performanceMonitor) {
        this.performanceMonitor = performanceMonitor;
    }

    public int getMaxConcurrentUsers() {
        return maxConcurrentUsers;
    }

    public void setMaxConcurrentUsers(int maxConcurrentUsers) {
        this.maxConcurrentUsers = maxConcurrentUsers;
    }

    public int getCurrentConcurrentUsers() {
        return currentConcurrentUsers;
    }

    public void setCurrentConcurrentUsers(int currentConcurrentUsers) {
        this.currentConcurrentUsers = currentConcurrentUsers;
    }

    public CulturalObjectDTO[] executeSearch(SearchCriteriaDTO criteria) {
        performanceMonitor.startTiming();
        try {
            List<CulturalObject> objects = culturalObjectRepository.findAll(criteria);
            CulturalObjectDTO[] dtos = mapToDTOs(objects);
            performanceMonitor.recordResponseTime();
            return dtos;
        } catch (ETOURConnectionException e) {
            handleException(e);
            ErrorDTO errorDto = new ErrorDTO("CONNECTION_ERROR", e.getMessage());
            throw new RuntimeException(errorDto.toString());
        }
    }

    public CulturalObjectDTO[] mapToDTOs(List<CulturalObject> objects) {
        if (objects == null) {
            return new CulturalObjectDTO[0];
        }
        CulturalObjectDTO[] dtos = new CulturalObjectDTO[objects.size()];
        for (int i = 0; i < objects.size(); i++) {
            dtos[i] = new CulturalObjectDTO(objects.get(i));
        }
        return dtos;
    }

    private void handleException(ETOURConnectionException e) {
        System.err.println("Handling connection exception: " + e.getMessage());
    }
}