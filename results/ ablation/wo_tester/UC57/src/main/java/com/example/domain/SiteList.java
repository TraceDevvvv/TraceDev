package com.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of sites with filtering and sorting capabilities.
 */
public class SiteList {
    private List<Site> sites;

    public SiteList() {
        this.sites = new ArrayList<>();
    }

    public SiteList(List<Site> sites) {
        this.sites = sites;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void addSite(Site site) {
        sites.add(site);
    }

    public void removeSite(Site site) {
        sites.remove(site);
    }

    /**
     * Sorts sites by distance from a given location string.
     * Assumes location string is "lat,lon".
     */
    public void sortByDistance(String location) {
        if (location == null || !location.contains(",")) {
            return;
        }
        String[] parts = location.split(",");
        double lat = Double.parseDouble(parts[0]);
        double lon = Double.parseDouble(parts[1]);
        sites.sort((s1, s2) -> {
            double d1 = s1.getDistanceFrom(lat, lon);
            double d2 = s2.getDistanceFrom(lat, lon);
            return Double.compare(d1, d2);
        });
    }

    /**
     * Filters sites by the given search criteria.
     */
    public SiteList filterByCriteria(SearchCriteria criteria) {
        List<Site> filtered = sites.stream()
                .filter(site -> criteria.getName() == null || criteria.getName().isEmpty() || site.getName().contains(criteria.getName()))
                .filter(site -> criteria.getCategory() == null || criteria.getCategory().isEmpty() || site.getCategory().equals(criteria.getCategory()))
                .filter(site -> site.getRating() >= criteria.getMinRating())
                .filter(site -> criteria.getAmenities() == null || criteria.getAmenities().isEmpty() || site.getAmenities().containsAll(criteria.getAmenities()))
                .collect(Collectors.toList());
        return new SiteList(filtered);
    }
}