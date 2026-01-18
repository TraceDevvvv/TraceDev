
package com.example.repository;

import com.example.model.Site;
import com.example.service.SiteService;

import java.sql.*;
import java.util.Random;

/**
 * Repository implementation for Site persistence.
 */
public class SiteRepository implements SiteService {
    private Connection databaseConnection;
    private Random random = new Random();

    public SiteRepository(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Site getCurrentSite(String siteId) {
        // TODO: Implement method
        return null;
    }

    @Override
    public boolean addSiteToVisitedList(String siteId, String userId) {
        // TODO: Implement method
        return false;
    }
}
