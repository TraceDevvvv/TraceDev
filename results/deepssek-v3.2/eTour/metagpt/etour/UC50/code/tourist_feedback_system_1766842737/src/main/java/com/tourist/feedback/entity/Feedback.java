package com.tourist.feedback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 *      
 *        feedbacks ，       
 *             、            
 *   ViewVisitedSites         
 */
@Entity
@Table(name = "feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    
    /**
     *        
     *         ，            ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *      ID
     *        users ，      
     */
    @NotNull(message = "  ID    ")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     *      ID
     *        sites ，      
     */
    @NotNull(message = "  ID    ")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id", nullable = false)
    private Site site;

    /**
     *     ，  1-5 
     * 1        ，5       
     */
    @NotNull(message = "      ")
    @Min(value = 1, message = "      1")
    @Max(value = 5, message = "      5")
    @Column(name = "rating", nullable = false)
    private Integer rating;

    /**
     *       
     *   TEXT         ，    2000  
     */
    @Size(max = 2000, message = "        2000   ")
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    /**
     *       
     *          ，           
     *    ViewVisitedSites    "    "
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     *       
     *             
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     *     
     * true:     ，false:       
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    /**
     *     （           ）
     *  created_at  ，created_at        
     *   ViewVisitedSites           
     */
    @Column(name = "visit_date")
    private LocalDateTime visitDate;

    /**
     *          
     * true:   ，false:    
     */
    @Column(name = "is_recommended")
    private Boolean isRecommended;

    /**
     *            
     *         ，       
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        //         ，           
        if (this.visitDate == null) {
            this.visitDate = LocalDateTime.now();
        }
        //         ，         
        if (this.isRecommended == null && this.rating != null) {
            this.isRecommended = this.rating >= 4; // 4        
        }
    }

    /**
     *            
     *         
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     *          
     *   ViewVisitedSites       ，           
     *        API    ，        
     */
    public String getSiteName() {
        return site != null ? site.getName() : null;
    }

    /**
     *              
     *      "yyyy-MM-dd"      
     *   ViewVisitedSites       
     */
    public String getFormattedVisitDate() {
        if (visitDate == null) {
            return null;
        }
        return visitDate.toLocalDate().toString();
    }

    /**
     *                
     *      "yyyy-MM-dd HH:mm:ss"        
     *          
     */
    public String getFormattedCreatedAt() {
        if (createdAt == null) {
            return null;
        }
        return createdAt.toString();
    }

    /**
     *           
     *                
     *             
     */
    public boolean isValid() {
        return user != null && 
               site != null && 
               rating != null && 
               rating >= 1 && 
               rating <= 5 && 
               createdAt != null;
    }

    /**
     *    toString  ，      
     *        ，          
     *               
     */
    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", userId=" + (user != null ? user.getId() : null) +
                ", siteId=" + (site != null ? site.getId() : null) +
                ", rating=" + rating +
                ", visitDate=" + (visitDate != null ? visitDate.toLocalDate() : null) +
                ", isActive=" + isActive +
                '}';
    }
}