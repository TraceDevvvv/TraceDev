package com.restaurant.menu.converter;

import com.restaurant.menu.Menu;
import com.restaurant.menu.MenuItem;
import com.restaurant.menu.dto.MenuDTO;
import com.restaurant.menu.dto.MenuItemDTO;
import com.restaurant.menu.dto.WeekMenuDTO;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Converts between entities and DTOs.
 */
public class DTOConverter {
    public MenuDTO toMenuDTO(Menu menu) {
        if (menu == null) return null;
        MenuDTO dto = new MenuDTO();
        dto.setMenuId(menu.getMenuId());
        dto.setDayOfWeek(menu.getDayOfWeek());
        dto.setDate(menu.getDate().toString());

        List<MenuItemDTO> itemDTOs = new ArrayList<>();
        for (MenuItem item : menu.getItems()) {
            MenuItemDTO itemDTO = new MenuItemDTO();
            itemDTO.setItemId(item.getItemId());
            itemDTO.setName(item.getName());
            itemDTO.setDescription(item.getDescription());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setCategory(item.getCategory());
            itemDTOs.add(itemDTO);
        }
        dto.setItems(itemDTOs);
        return dto;
    }

    public Menu toMenu(MenuDTO dto) {
        if (dto == null) return null;
        // Assumption: date string is in a format parseable by Date constructor.
        // For simplicity, using current date.
        Menu menu = new Menu(dto.getDayOfWeek(), new java.util.Date());
        // Note: menuId might be set from DTO if needed.
        if (dto.getMenuId() != null) {
            // In real scenario, we might fetch existing menu and update it.
        }
        if (dto.getItems() != null) {
            List<MenuItem> items = new ArrayList<>();
            for (MenuItemDTO itemDTO : dto.getItems()) {
                MenuItem item = new MenuItem(itemDTO.getName(), itemDTO.getDescription(),
                                             itemDTO.getPrice(), itemDTO.getCategory());
                items.add(item);
            }
            menu.setItems(items);
        }
        return menu;
    }

    public WeekMenuDTO toWeekMenuDTO(List<Menu> menus) {
        WeekMenuDTO weekDTO = new WeekMenuDTO();
        Map<String, MenuDTO> dayMap = new HashMap<>();
        for (Menu menu : menus) {
            dayMap.put(menu.getDayOfWeek(), toMenuDTO(menu));
        }
        weekDTO.setDays(dayMap);
        return weekDTO;
    }
}