package com.agency.report.service;

import com.agency.report.model.Feedback;
import com.agency.report.model.Location;
import com.agency.report.model.ReportStatistic;
import com.agency.report.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;

@Service
public class ReportService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private LocationService locationService;

    public ReportStatistic generateReport(Long locationId) {
        Location location = locationService.getLocationById(locationId)
                .orElseThrow(() -> new NoSuchElementException("Location not found with ID: " + locationId));

        List<Feedback> feedbacks = feedbackRepository.findByLocation(location);

        long totalFeedbacks = feedbacks.size();
        OptionalDouble averageRating = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average();

        return new ReportStatistic(
                location.getId(),
                location.getName(),
                averageRating.orElse(0.0),
                totalFeedbacks
        );
    }
}
