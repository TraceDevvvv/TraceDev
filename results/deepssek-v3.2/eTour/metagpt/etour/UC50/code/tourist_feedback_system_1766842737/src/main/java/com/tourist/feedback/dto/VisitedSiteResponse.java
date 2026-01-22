package com.tourist.feedback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ViewVisitedSites           
 *             ：  ID、  、    、          
 *   ViewVisitedSites           ，       API  
 * 
 *   API        ：
 * {
 *   "siteId": 1,
 *   "siteName": "     ",
 *   "visitedDate": "2024-01-15",
 *   "feedbackRating": 5,
 *   "feedbackComment": "      ！"
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "            ")
public class VisitedSiteResponse {
    
    /**
     *        
     *               
     */
    @Schema(description = "  ID", example = "1")
    private Long siteId;
    
    /**
     *     
     *       ，            
     */
    @Schema(description = "    ", example = "     ")
    private String siteName;
    
    /**
     *       
     *            ，          
     */
    @Schema(description = "      ", example = "        ")
    private String siteLocation;
    
    /**
     *     
     *        ，     、    、    
     */
    @Schema(description = "    ", example = "    ")
    private String siteCategory;
    
    /**
     *       
     *             ，       "yyyy-MM-dd"  
     *   ISO        JSON       
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(description = "    ", example = "2024-01-15")
    private LocalDateTime visitedDate;
    
    /**
     *     （1-5 ）
     *    1 （     ） 5 （    ）
     *              
     */
    @Schema(description = "    （1-5 ）", example = "5")
    private Integer feedbackRating;
    
    /**
     *       
     *                 
     *     ，            
     */
    @Schema(description = "    ", example = "      ！      ！")
    private String feedbackComment;
    
    /**
     *       
     *          ，          
     *                
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "      ", example = "2024-01-16 14:30:00")
    private LocalDateTime feedbackCreatedAt;
    
    /**
     *          
     * true:  ，false:   
     *            ，        
     */
    @Schema(description = "    ", example = "true")
    private Boolean isRecommended;
    
    /**
     *     URL
     *          ，      
     *     ，          
     */
    @Schema(description = "    URL", example = "https://example.com/images/eiffel-tower.jpg")
    private String siteImageUrl;
    
    /**
     *             
     *  1-5            （ ★★★★★）
     *         
     * 
     * @return      ，  "★★★★★"  5 
     */
    public String getRatingStars() {
        if (feedbackRating == null || feedbackRating < 1) {
            return "";
        }
        
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < feedbackRating; i++) {
            stars.append("★");
        }
        //     
        for (int i = feedbackRating; i < 5; i++) {
            stars.append("☆");
        }
        return stars.toString();
    }
    
    /**
     *           （    ）
     *              
     * 
     * @return    "yyyy-MM-dd"      
     */
    public String getShortVisitedDate() {
        if (visitedDate == null) {
            return null;
        }
        return visitedDate.toLocalDate().toString();
    }
    
    /**
     *            
     *               
     * 
     * @return true       ，false      
     */
    public boolean hasComment() {
        return feedbackComment != null && !feedbackComment.trim().isEmpty();
    }
    
    /**
     *       （   50   ）
     *            
     *       50   ，  "…"    
     * 
     * @return        
     */
    public String getCommentPreview() {
        if (!hasComment()) {
            return "";
        }
        
        if (feedbackComment.length() <= 50) {
            return feedbackComment;
        }
        
        return feedbackComment.substring(0, 50) + "…";
    }
    
    /**
     *           
     *          ，        
     * 
     * @return true        ，false       
     */
    public boolean isValid() {
        return siteId != null && 
               siteName != null && 
               !siteName.trim().isEmpty() && 
               visitedDate != null && 
               feedbackRating != null &&
               feedbackRating >= 1 && 
               feedbackRating <= 5;
    }
    
    /**
     *     ：              
     *              ，        
     * 
     * @param siteId   ID
     * @param siteName     
     * @param siteLocation     
     * @param siteCategory     
     * @param visitedDate     
     * @param feedbackRating   
     * @param feedbackComment   
     * @param feedbackCreatedAt       
     * @param isRecommended     
     * @param siteImageUrl     URL
     * @return     VisitedSiteResponse  
     */
    public static VisitedSiteResponse build(
            Long siteId, 
            String siteName, 
            String siteLocation,
            String siteCategory,
            LocalDateTime visitedDate, 
            Integer feedbackRating, 
            String feedbackComment,
            LocalDateTime feedbackCreatedAt,
            Boolean isRecommended,
            String siteImageUrl) {
        
        VisitedSiteResponse response = new VisitedSiteResponse();
        response.siteId = siteId;
        response.siteName = siteName;
        response.siteLocation = siteLocation;
        response.siteCategory = siteCategory;
        response.visitedDate = visitedDate;
        response.feedbackRating = feedbackRating;
        response.feedbackComment = feedbackComment;
        response.feedbackCreatedAt = feedbackCreatedAt;
        response.isRecommended = isRecommended;
        response.siteImageUrl = siteImageUrl;
        
        return response;
    }
    
    /**
     *             
     *          
     * 
     * @return              
     */
    @Override
    public String toString() {
        return String.format(
            "VisitedSiteResponse{siteId=%d, siteName='%s', visitedDate=%s, rating=%d}",
            siteId, siteName, 
            visitedDate != null ? visitedDate.toLocalDate() : null, 
            feedbackRating
        );
    }
}