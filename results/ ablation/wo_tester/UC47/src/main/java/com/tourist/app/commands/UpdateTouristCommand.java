package com.tourist.app.commands;

import com.tourist.app.dtos.TouristDto;

/**
 * Command for updating a tourist profile.
 */
public class UpdateTouristCommand {
    private String touristId;
    private TouristDto updatedData;

    /**
     * Constructor.
     * @param id the tourist id
     * @param data the updated data
     */
    public UpdateTouristCommand(String id, TouristDto data) {
        this.touristId = id;
        this.updatedData = data;
    }

    public String getTouristId() {
        return touristId;
    }

    public TouristDto getUpdatedData() {
        return updatedData;
    }
}