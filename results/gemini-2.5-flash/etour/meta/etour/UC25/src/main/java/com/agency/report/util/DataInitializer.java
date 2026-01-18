package com.agency.report.util;

import com.agency.report.model.Feedback;
import com.agency.report.model.Location;
import com.agency.report.repository.FeedbackRepository;
import com.agency.report.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize Locations
        Location location1 = new Location();
        location1.setName("Central Park");
        location1.setAddress("New York, NY");

        Location location2 = new Location();
        location2.setName("Golden Gate Bridge");
        location2.setAddress("San Francisco, CA");

        locationRepository.saveAll(Arrays.asList(location1, location2));

        // Initialize Feedback for Location 1
        Feedback feedback1_1 = new Feedback();
        feedback1_1.setComment("Beautiful park, great for a stroll.");
        feedback1_1.setRating(5);
        feedback1_1.setTimestamp(LocalDateTime.now().minusDays(10));
        feedback1_1.setLocation(location1);

        Feedback feedback1_2 = new Feedback();
        feedback1_2.setComment("A bit crowded but lovely.");
        feedback1_2.setRating(4);
        feedback1_2.setTimestamp(LocalDateTime.now().minusDays(5));
        feedback1_2.setLocation(location1);

        // Initialize Feedback for Location 2
        Feedback feedback2_1 = new Feedback();
        feedback2_1.setComment("Iconic landmark, stunning views.");
        feedback2_1.setRating(5);
        feedback2_1.setTimestamp(LocalDateTime.now().minusDays(8));
        feedback2_1.setLocation(location2);

        Feedback feedback2_2 = new Feedback();
        feedback2_2.setComment("Foggy but still impressive.");
        feedback2_2.setRating(4);
        feedback2_2.setTimestamp(LocalDateTime.now().minusDays(3));
        feedback2_2.setLocation(location2);

        feedbackRepository.saveAll(Arrays.asList(feedback1_1, feedback1_2, feedback2_1, feedback2_2));
    }
}
