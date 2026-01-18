package com.example.domain;

/**
 * Value object representing a site ID.
 */
public class SiteId {
    private int value;

    public SiteId(int id) {
        this.value = id;
    }

    public int getValue() {
        return value;
    }
}