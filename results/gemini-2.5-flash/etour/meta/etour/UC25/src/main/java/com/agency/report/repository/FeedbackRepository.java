package com.agency.report.repository;

import com.agency.report.model.Feedback;
import com.agency.report.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByLocation(Location location);
}
