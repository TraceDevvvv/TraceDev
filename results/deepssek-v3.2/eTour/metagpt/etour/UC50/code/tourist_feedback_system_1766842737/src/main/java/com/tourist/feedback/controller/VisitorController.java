package com.tourist.feedback.controller;

import com.tourist.feedback.dto.VisitedSiteResponse;
import com.tourist.feedback.service.VisitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ViewVisitedSites   REST   
 *                API  ，        、    、           
 *       ViewVisitedSites     REST API  
 * 
 *     ：
 * 1.             （  API）
 * 2.          API
 * 3.        API
 * 4.          API
 * 5.        API
 * 6.               
 * 7.          API
 * 
 *   PRD       ，    API  ：
 * GET /api/users/{userId}/visited-sites -             
 */
@RestController
@RequestMapping("/api/users/{userId}/visited-sites")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Visitor API", description = "ViewVisitedSites   REST API  ")
public class VisitorController {
    
    private final VisitorService visitorService;
    
    /**
     *             （  API  ）
 *   ViewVisitedSites       ：              
 * 
 * @param userId        ，         ID
 * @param pageable     ，    、         
 *      ViewVisitedSites      ，           
     * 
     * @return              
     */
    @GetMapping
    @Operation(summary = "            ", description = "    ID                 ")
    public ResponseEntity<?> getVisitedSites(
            @Parameter(description = "  ID", required = true, example = "1")
            @PathVariable Long userId,
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("              ，  ID: {},     : {}", userId, pageable);
        
        try {
            //   Service             
            List<VisitedSiteResponse> visitedSites = visitorService.getVisitedSites(userId, pageable);
            
            //       
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            log.debug("            ，    : {}", visitedSites.size());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("               ，  ID: {}", userId, e);
            
            //               
            return handleServiceException(e);
        }
    }
    
    /**
     *             （       ）
 *                ，      "        "        
     * 
     * @param userId        
     * @param startDate     （  ），   yyyy-MM-dd HH:mm:ss")
     * 
     * @return                 
     */
    @GetMapping("/by-date-range")
    @Operation(summary = "             ", description = "    ID                   
     */
    @GetMapping("/by-date-range")
    public ResponseEntity<?> getVisitedSitesByDateRange(
            @Parameter(description = "  ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "    （  ：yyyy-MM-dd HH:mm:ss）")
            @RequestParam(required = false) 
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @Parameter(description = "    （  ：yyyy-MM-dd HH:mm:ss）")
            @RequestParam(required = false) 
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate,
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("           ，  ID: {},     : {},     : {}", userId, startDate, endDate),
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("           ，  ID: {},     : {},     : {}", userId, startDate, endDate);
        
        try {
            //   Service        
            List<VisitedSiteResponse> visitedSites = visitorService.getVisitedSitesByDateRange(userId, startDate, endDate, pageable);
            
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("                  ", e);
            return handleServiceException(e);
        }
    }
    
    /**
     *             （     ）
     *              ，  P1           
     * 
     * @return                 
     */
    @GetMapping("/by-rating")
    @Operation(summary = "           ", description = "    ID                   
     */
    @GetMapping("/by-rating")
    public ResponseEntity<?> getVisitedSitesByRating(
            @Parameter(description = "  ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "    （1-5）", example = "1")
            @RequestParam(required = false) Integer minRating,
            @Parameter(description = "    （1-5）")
            @RequestParam(required = false) Integer maxRating,
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("           ，  ID: {},     : {},     : {}", userId, minRating, maxRating),
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("           ，  ID: {},     : {},     : {}", userId, minRating, maxRating);
        
        try {
            //   Service        
            List<VisitedSiteResponse> visitedSites = visitorService.getVisitedSitesByRating(userId, minRating, maxRating, pageable);
            
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("                  ", e);
            return handleServiceException(e);
        }
    }
    
    /**
     *             
     *       "      "   ，              
     * 
     * @return             
     */
    @GetMapping("/recommended")
    @Operation(summary = "            ", description = "    ID           
     */
    @GetMapping("/recommended")
    public ResponseEntity<?> getRecommendedSites(
            @Parameter(description = "  ID", required = true, example = "1")
            @PathVariable Long userId,
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("            ，  ID: {}", userId);
        
        try {
            List<VisitedSiteResponse> visitedSites = visitorService.getRecommendedSites(userId, pageable);
            
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("             ", e);
            return handleServiceException(e);
        }
    }
    
    /**
     *              
     *   ViewVisitedSites       ，           
     * 
     * @return              
     */
    @GetMapping("/recent")
    @Operation(summary = "             ", description = "    ID            
     */
    @GetMapping("/recent")
    public ResponseEntity<?> getRecentlyVisitedSites(
            @Parameter(description = "  ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "        （  ）", example = "10")
            @RequestParam(required = false) Integer limit) {
        
        log.info("              ，  ID: {},     : {}", userId, limit);
        
        try {
            List<VisitedSiteResponse> visitedSites = visitorService.getRecentlyVisitedSites(userId, limit);
            
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("               ", e);
            return handleServiceException(e);
        }
    }
    
    /**
     *               
     *   ViewVisitedSites       ，         
     * 
     * @return              
     */
    @GetMapping("/stats")
    public ResponseEntity<?> getVisitedSiteStats(
            @Parameter(description = "  ID", required = true, example = "1")
            @PathVariable Long userId) {
        
        log.info("              ，  ID: {}", userId);
        
        try {
            VisitedSiteStats stats = visitorService.getVisitedSiteStats(userId);
            
            ApiResponse<VisitedSiteStats> response = ApiResponse.success(stats);
            return ResponseEntity.ok(response);
        }
    }
    
    /**
     *           
     *        、         
     * 
     * @return                 
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchVisitedSites(
            @Parameter(description = "  ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "     （    、   ）", required = true, example = "     ")
            @RequestParam String keyword,
            @PageableDefault(size = 10, sort = "visitedDate") Pageable pageable) {
        
        log.info("            ，  ID: {},    : {}", userId, keyword);
        
        try {
            List<VisitedSiteResponse> visitedSites = visitorService.searchVisitedSites(userId, keyword, pageable);
            
            ApiResponse<List<VisitedSiteResponse>> response = ApiResponse.success(visitedSites);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("             ", e);
            return handleServiceException(e);
        }
    }
    
    /**
     *             
     *      CSV  ，      "      "            
     * 
     * @return          
     */
    @GetMapping("/export")
    public ResponseEntity<?> exportVisitedSites(
            @Parameter(description = "  ID", required = true, example = "1")
            @PathVariable Long userId,
            @Parameter(description = "    （CSV/JSON ）", example = "CSV")
            @RequestParam(defaultValue = "CSV") String format) {
        
        log.info("            ，  ID: {},   : {}", userId, format);
        
        try {
            String exportData = visitorService.exportVisitedSites(userId, format);
            
            //         
            return ResponseEntity.ok()
                    .header("Content-Type", "text/csv; charset=UTF-8")
                    .header("Content-Disposition", "attachment; filename=visited_sites." + format.toLowerCase())
                    .body(exportData);
            
        } catch (Exception e) {
            log.error("             ", e);
            return handleServiceException(e);
        }
    }
    
    /**
     *            
     *                   
     *                   
     * 
     * @param e      
     * @return       
     */
    private ResponseEntity<ApiResponse<?>> handleServiceException(Exception e) {
        
        log.error("      ", e);
        
        if (e instanceof AuthenticationException) {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "UNAUTHORIZED", "           ", "     ");
            
        } else if (e instanceof UserNotFoundException) {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "USER_NOT_FOUND", "             ", "     ID    ");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
            
        } else if (e instanceof DataAccessException) {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "DATA_ACCESS_ERROR", "      ，     ");
        } else if (e instanceof ServiceUnavailableException) {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "SERVICE_UNAVAILABLE", "       ，       ");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
            
        } else {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "INTERNAL_SERVER_ERROR", "       ，      ");
            
        } else {
            ApiResponse<?> errorResponse = ApiResponse.error(
                    "UNKNOWN_ERROR", "        ，     ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}