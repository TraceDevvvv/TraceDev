package com.example;

import java.util.Arrays;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;

/**
 * Main class to demonstrate the system running.
 * Creates necessary components and simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup data source (using H2 in-memory for demonstration)
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        // Create required serv
        ETOURServer server = new ETOURServer();
        AuthenticationService authService = new ConcreteAuthService();
        MenuValidationStrategy validator = new StandardMenuValidator(1);
        MenuRepository repo = new MenuRepositoryImpl(dataSource, server);

        // Create controller and view
        ModifyMenuController controller = new ModifyMenuController(repo, validator, authService);
        MenuFormView view = new MenuFormView(controller);

        // Simulate the sequence diagram flow
        System.out.println("=== Simulating Menu Modification Use Case ===\n");

        // Step 1: Operator activates menu editing
        view.activateMenuEditing();

        // Step 2-3: Operator selects a day
        view.selectDayOfWeek("Monday");

        // Step 4-7: System loads menu (in this simulation, no pre-existing data)

        // Step 8: Operator submits edited form
        MenuDTO editedMenu = new MenuDTO("Monday", Arrays.asList("Burger", "Fries", "Salad"));
        view.submitForm(editedMenu);

        // Step 13 (or error): Show result
        view.showSuccessNotification();

        System.out.println("\n=== Simulation Complete ===");
    }
}