package com.example;

import com.example.agency.AgencyOperator;
import com.example.controller.ViewTouristCardController;
import com.example.datasource.DataSource;
import com.example.repository.TouristRepository;
import com.example.repository.TouristRepositoryImpl;
import com.example.dto.TouristDTO;
import java.util.List;

/**
 * Main class to simulate the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        DataSource dataSource = new DataSource("jdbc:mysql://localhost:3306/etour", "user", "password");
        TouristRepository repository = new TouristRepositoryImpl(dataSource);
        ViewTouristCardController controller = new ViewTouristCardController(repository);
        AgencyOperator operator = new AgencyOperator("op1", "John Operator", controller);

        // Simulate the sequence diagram steps

        // 1. Login (Entry Condition)
        operator.login();

        // 2. Search tourist
        List<TouristDTO> list = operator.searchTourist("John");
        if (list.isEmpty()) {
            System.out.println("No tourists to select. Exiting.");
            return;
        }

        // 3. Select a tourist (first in list)
        int selectedId = list.get(0).getId();
        operator.selectTourist(selectedId);

        // 4. Display tourist card (Flow of Events: 4)
        // In a real scenario, we would pass the selected TouristDTO to the display method.
        // For demonstration, we'll call displayTouristCard on the controller directly with the first DTO.
        controller.displayTouristCard(list.get(0));
    }
}