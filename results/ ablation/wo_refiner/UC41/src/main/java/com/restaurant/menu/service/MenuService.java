package com.restaurant.menu.service;

import com.restaurant.menu.Menu;
import com.restaurant.menu.dto.MenuDTO;
import com.restaurant.menu.dto.WeekMenuDTO;
import com.restaurant.menu.repository.MenuRepository;
import com.restaurant.menu.validation.MenuValidator;
import com.restaurant.menu.exception.ServerConnectionException;
import com.restaurant.menu.exception.ServiceUnavailableException;
import com.restaurant.menu.converter.DTOConverter;
import java.util.List;

/**
 * Service layer for menu operations.
 */
public class MenuService {
    private MenuRepository menuRepository;
    private DTOConverter dtoConverter;
    private MenuValidator validator;

    public MenuService(MenuRepository menuRepository, DTOConverter dtoConverter) {
        this.menuRepository = menuRepository;
        this.dtoConverter = dtoConverter;
        this.validator = new MenuValidator(); // Instantiate validator for service-level validation
    }

    public WeekMenuDTO getWeekMenu() {
        // Simulate potential connection issue (for requirement R16)
        if (Math.random() < 0.05) { // 5% chance to simulate connection loss
            throw new ServerConnectionException("Connection lost while fetching week menu.");
        }
        List<Menu> menus = menuRepository.findAllWeekMenus();
        return dtoConverter.toWeekMenuDTO(menus);
    }

    public Menu getMenuByDay(String day) {
        return menuRepository.findByDay(day);
    }

    public boolean updateMenu(MenuDTO menuDTO) {
        // Convert DTO to entity
        Menu menu = dtoConverter.toMenu(menuDTO);
        // Validate menu data
        if (!validateMenuData(menu)) {
            return false;
        }
        // Save to repository
        menuRepository.save(menu);
        return true;
    }

    public boolean validateMenuData(Menu menu) {
        return validator.validateRequiredFields(menu) && validator.validateItemPr(menu.getItems());
    }

    /**
     * Handles connection exceptions (requirement R16).
     */
    public void handleConnectionException(ServerConnectionException ex) {
        // Log the exception, notify monitoring, etc.
        System.err.println("Handling connection exception: " + ex.getMessage());
        // Re-throw as ServiceUnavailableException
        throw new ServiceUnavailableException("Service unavailable due to connection issues.");
    }
}