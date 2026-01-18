package com.example.etour.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Tourist actor with a card and a list of bookmarked sites.
 */
public class Tourist {
    private final String touristId;
    private final String name;
    private final List<Site> bookmarks;
    private Site currentSite;

    public Tourist(String touristId, String name) {
        this.touristId = touristId;
        this.name = name;
        this.bookmarks = new ArrayList<>();
    }

    public void setCurrentSite(Site site) {
        this.currentSite = site;
    }

    public Site getCurrentSite() {
        return currentSite;
    }

    public List<Site> getBookmarks() {
        return new ArrayList<>(bookmarks);
    }

    public boolean addBookmark(Site site) {
        if (site == null) return false;
        if (bookmarks.stream().anyMatch(s -> s.getSiteId().equals(site.getSiteId()))) {
            return false; // already bookmarked
        }
        bookmarks.add(site);
        return true;
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "touristId='" + touristId + '\'' +
                ", name='" + name + '\'' +
                ", currentSite=" + currentSite +
                ", bookmarks=" + bookmarks +
                '}';
    }
}