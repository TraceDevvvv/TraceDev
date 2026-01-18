package com.example.application;

import com.example.domain.DailyMenu;
import com.example.domain.MenuItem;
import com.example.presentation.MenuViewModel;

import java.util.stream.Collectors;

/**
 * Mapper for converting between DailyMenu domain objects and MenuViewModel presentation objects.
 * Added to satisfy R12, R16.
 */
public class MenuMapper {

    /**
     * Converts a MenuViewModel to a DailyMenu domain object.
     * @param viewModel The MenuViewModel from the presentation layer.
     * @return A DailyMenu domain object.
     */
    public DailyMenu toDailyMenu(MenuViewModel viewModel) {
        System.out.println("[MenuMapper] Mapping MenuViewModel to DailyMenu for day: " + viewModel.getSelectedDay());
        if (viewModel == null) {
            return null;
        }
        return new DailyMenu(
                viewModel.getSelectedDay(),
                viewModel.getMenuItems()
                        .stream()
                        .map(item -> new MenuItem(item.getName(), item.getDescription(), item.getPrice(), item.isAvailable()))
                        .collect(Collectors.toList())
        );
    }

    /**
     * Converts a DailyMenu domain object to a MenuViewModel for the presentation layer.
     * @param dailyMenu The DailyMenu domain object.
     * @return A MenuViewModel.
     */
    public MenuViewModel toMenuViewModel(DailyMenu dailyMenu) {
        System.out.println("[MenuMapper] Mapping DailyMenu to MenuViewModel for day: " + dailyMenu.getDay());
        if (dailyMenu == null) {
            return new MenuViewModel(); // Return an empty view model
        }
        return new MenuViewModel(
                dailyMenu.getDay(),
                dailyMenu.getMenuItems()
                        .stream()
                        .map(item -> new MenuItem(item.getName(), item.getDescription(), item.getPrice(), item.isAvailable()))
                        .collect(Collectors.toList()),
                null // Message field is handled separately, not directly mapped from DailyMenu
        );
    }
}