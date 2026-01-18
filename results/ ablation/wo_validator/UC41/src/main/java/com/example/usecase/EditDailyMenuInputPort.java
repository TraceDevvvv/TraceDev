
package com.example.usecase;

import com.example.dto.MenuDTO;

/**
 * Input port interface for editing daily menu use case.
 */
public interface EditDailyMenuInputPort {
    /**
     * Executes the edit daily menu use case.
     *
     * @param operatorId The ID of the operator performing the action
     * @param dayOfWeek  The day of the week for the menu
     * @param menuData   The menu data to edit
     * @return The result of the operation
     */
    EditDailyMenuResult execute(Long operatorId, String dayOfWeek, MenuDTO menuData);
}
