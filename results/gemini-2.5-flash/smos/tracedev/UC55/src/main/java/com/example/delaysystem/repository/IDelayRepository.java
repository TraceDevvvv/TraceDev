package com.example.delaysystem.repository;

import com.example.delaysystem.model.DelayData;
import java.util.List;

/**
 * Interface for managing delay data persistence.
 * Defines the contract for saving delay information.
 */
public interface IDelayRepository {
    /**
     * Saves a list of DelayData objects to the persistent storage.
     *
     * @param delayDataList A list of DelayData objects to be saved.
     * @return true if the data was successfully saved, false otherwise.
     */
    boolean saveDelayData(List<DelayData> delayDataList);
}