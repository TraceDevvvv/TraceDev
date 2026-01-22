package com.tourist.feedback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *      
 *        users ，         
 *      、  、         
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    /**
     *        
     *         
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *    ，        
     *      2-50     
     */
    @NotBlank(message = "       ")
    @Size(min = 2, max = 50, message = "        2-50     ")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     *     ，         
     *          
     */
    @NotBlank(message = "      ")
    @Email(message = "       ")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     *      
     *   BCrypt    ，      
     */
    @NotBlank(message = "      ")
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    /**
     *       
     *          
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     *           
     *               
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     *     
     * true:     ，false:     
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    /**
     *            
     *              
     *          
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    /**
     *            
     *              
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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
     *    toString  ，        
     *          ，       
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}