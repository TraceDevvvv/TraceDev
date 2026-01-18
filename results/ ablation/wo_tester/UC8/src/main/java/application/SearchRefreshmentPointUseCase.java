package application;

import interfaces.IRefreshmentPointRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Use case for searching refreshment points (Flow step 1).
 */
public class SearchRefreshmentPointUseCase {
    private IRefreshmentPointRepository pointRepository;

    public SearchRefreshmentPointUseCase(IRefreshmentPointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    /**
     * Searches points based on criteria.
     * Assumption: Criteria map is not implemented; returns all points.
     * @param criteria the search criteria.
     * @return list of DTOs.
     */
    public List<RefreshmentPointDTO> searchPoints(Map<String, Object> criteria) {
        // For simplicity, return all points. In a real scenario, filter by criteria.
        return pointRepository.findAll().stream()
                .map(point -> {
                    RefreshmentPointDTO dto = new RefreshmentPointDTO();
                    dto.fromEntity(point);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * Gets all active points.
     * @return list of DTOs for active points.
     */
    public List<RefreshmentPointDTO> getActivePoints() {
        return pointRepository.findAll().stream()
                .filter(RefreshmentPoint::isActive)
                .map(point -> {
                    RefreshmentPointDTO dto = new RefreshmentPointDTO();
                    dto.fromEntity(point);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}