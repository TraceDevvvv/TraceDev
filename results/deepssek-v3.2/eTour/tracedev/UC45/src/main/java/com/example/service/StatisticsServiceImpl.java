package com.example.service;

import com.example.domain.RefreshmentPoint;
import com.example.dto.PersonalStatisticsDTO;
import com.example.entity.StatisticsData;
import com.example.exception.ServiceException;
import com.example.repository.StatisticsRepository;

import java.util.Date;

/**
 * Implementation of StatisticsService.
 * Retrieves statistics data for refreshment point associated with operator.
 */
public class StatisticsServiceImpl implements StatisticsService {

    private StatisticsRepository statisticsRepository;

    /**
     * Constructor with dependency injection.
     */
    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public PersonalStatisticsDTO getPersonalStatistics(String operatorId) {
        try {
            // Retrieve statistics data from repository
            StatisticsData statisticsData = statisticsRepository.findByOperatorId(operatorId);

            if (statisticsData == null) {
                // No data found: create empty DTO (alternative flow)
                PersonalStatisticsDTO emptyDTO = createEmptyDTO();
                validateDTO(emptyDTO);
                return emptyDTO;
            }

            // Convert to DTO
            PersonalStatisticsDTO dto = convertToDTO(statisticsData);
            validateDTO(dto);
            return dto;
        } catch (Exception e) {
            // Wrap any exception in ServiceException as per sequence diagram
            throw new ServiceException("Error retrieving personal statistics", e);
        }
    }

    /**
     * Converts StatisticsData entity to PersonalStatisticsDTO.
     * Sequence diagram step: convertToDTO(statisticsData)
     */
    public PersonalStatisticsDTO convertToDTO(StatisticsData statisticsData) {
        PersonalStatisticsDTO dto = new PersonalStatisticsDTO();
        dto.setOperatorId(statisticsData.getOperatorId());
        dto.setRefreshmentPointId(statisticsData.getRefreshmentPointId());
        dto.setRefreshmentPointName(statisticsData.getRefreshmentPointName());

        // Simulate calculation of total sales from salesData map
        double totalSales = statisticsData.getSalesData().values().stream()
                .mapToDouble(Double::doubleValue).sum();
        dto.setTotalSales(totalSales);

        // Simulate calculation of average order value
        int orderCount = statisticsData.getOrderCounts();
        dto.setNumberOfOrders(orderCount);
        dto.setAverageOrderValue(orderCount > 0 ? totalSales / orderCount : 0.0);

        // Use calculation date as period end, start date as 30 days earlier (example)
        Date endDate = statisticsData.getCalculationDate();
        dto.setPeriodEndDate(endDate);
        Date startDate = new Date(endDate.getTime() - 30L * 24 * 60 * 60 * 1000);
        dto.setPeriodStartDate(startDate);

        return dto;
    }

    /**
     * Creates an empty DTO when no statistics data is found.
     * Sequence diagram step: createEmptyDTO()
     */
    public PersonalStatisticsDTO createEmptyDTO() {
        PersonalStatisticsDTO dto = new PersonalStatisticsDTO();
        dto.setOperatorId("N/A");
        dto.setRefreshmentPointId("N/A");
        dto.setRefreshmentPointName("No data");
        dto.setTotalSales(0.0);
        dto.setAverageOrderValue(0.0);
        dto.setNumberOfOrders(0);
        dto.setPeriodStartDate(new Date());
        dto.setPeriodEndDate(new Date());
        return dto;
    }

    /**
     * Validates the DTO for required fields.
     * Added to satisfy requirement Flow of Events Step 3.
     */
    public boolean validateDTO(PersonalStatisticsDTO dto) {
        // Simple validation: ensure required fields are not null.
        if (dto.getOperatorId() == null || dto.getRefreshmentPointId() == null) {
            throw new IllegalArgumentException("Invalid DTO: missing required fields");
        }
        return true;
    }
}