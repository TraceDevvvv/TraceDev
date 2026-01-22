package com.tourist.feedback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *   （  ）   
 *        sites ，           
 *       、  、       
 */
@Entity
@Table(name = "sites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Site {
    
    /**
     *        
     *         ，          ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *     ，        
     *      2-100     ，      
     */
    @NotBlank(message = "        ")
    @Size(min = 2, max = 100, message = "         2-100     ")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /**
     *       
     *   TEXT           
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     *       
     *          ，     255   
     */
    @NotBlank(message = "        ")
    @Size(max = 255, message = "          255   ")
    @Column(name = "location", nullable = false)
    private String location;

    /**
     *     （ ：    、    、    ）
     *        
     */
    @Column(name = "category")
    private String category;

    /**
     *       
     *          ，        
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     *     URL
     *            
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     *       
     *                
     */
    @Column(name = "average_rating", precision = 3, scale = 2)
    private Double averageRating;

    /**
     *         
     *              
     */
    @Column(name = "visit_count", nullable = false)
    private Integer visitCount = 0;

    /**
     *            
     *              
     *            
     */
    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    /**
     *            
     *         ，       
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        //        null，    0.0
        if (this.averageRating == null) {
            this.averageRating = 0.0;
        }
        //        null，    0
        if (this.visitCount == null) {
            this.visitCount = 0;
        }
    }

    /**
     *       
     *                   
     *      Service   ，         
     */
    public void updateAverageRating() {
        if (feedbacks == null || feedbacks.isEmpty()) {
            this.averageRating = 0.0;
            return;
        }
        
        double sum = feedbacks.stream()
                .filter(feedback -> feedback.getRating() != null)
                .mapToInt(Feedback::getRating)
                .sum();
        
        long count = feedbacks.stream()
                .filter(feedback -> feedback.getRating() != null)
                .count();
        
        if (count > 0) {
            this.averageRating = sum / count;
        } else {
            this.averageRating = 0.0;
        }
    }

    /**
     *       
     *                   
     */
    public void incrementVisitCount() {
        if (this.visitCount == null) {
            this.visitCount = 0;
        }
        this.visitCount++;
    }

    /**
     *    toString  ，         
     *        ，      
     */
    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", averageRating=" + averageRating +
                ", visitCount=" + visitCount +
                '}';
    }
}