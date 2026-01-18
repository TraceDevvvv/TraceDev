package com.agency.report.controller;

import com.agency.report.model.ReportStatistic;
import com.agency.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/statistics/{locationId}")
    public ResponseEntity<ReportStatistic> getReportStatistic(@PathVariable Long locationId) {
        try {
            ReportStatistic statistic = reportService.generateReport(locationId);
            return ResponseEntity.ok(statistic);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
