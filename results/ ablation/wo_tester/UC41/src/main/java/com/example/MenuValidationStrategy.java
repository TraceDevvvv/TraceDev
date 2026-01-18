package com.example;

/**
 * Strategy interface for validating menu data.
 */
public interface MenuValidationStrategy {
    boolean validate(MenuDTO menuData);
}