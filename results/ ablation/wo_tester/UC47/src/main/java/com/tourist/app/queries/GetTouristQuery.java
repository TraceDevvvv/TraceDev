package com.tourist.app.queries;

/**
 * Query for retrieving a tourist.
 */
public class GetTouristQuery {
    private String touristId;

    /**
     * Constructor.
     * @param id the tourist id
     */
    public GetTouristQuery(String id) {
        this.touristId = id;
    }

    public String getTouristId() {
        return touristId;
    }
}