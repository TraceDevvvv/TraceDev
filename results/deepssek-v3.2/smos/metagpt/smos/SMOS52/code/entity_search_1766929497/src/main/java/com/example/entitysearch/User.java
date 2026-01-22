package com.example.entitysearch;

/**
 *      
 *           ，   Entity  
 *             ，    、  、   
 */
public class User extends Entity {
    private String username;         //    
    private String email;            //     
    private String role;             //     （   、  、   ）
    private String phoneNumber;      //     
    private boolean isActive;        //       
    private String department;       //     
    
    /**
     *     
     * 
     * @param id       
     * @param name     
     * @param description     
     * @param username    
     * @param email     
     * @param role     
     * @param phoneNumber     
     * @param isActive       
     * @param department     
     */
    public User(String id, String name, String description,
               String username, String email, String role,
               String phoneNumber, boolean isActive, String department) {
        super(id, name, description);
        this.username = username;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.department = department;
    }
    
    /**
     *       
     *          
     * 
     * @return         "  "
     */
    @Override
    public String getType() {
        return "  ";
    }
    
    /**
     *                 
     *       ，           
     * 
     * @param keyword        
     * @return            true，    false
     */
    @Override
    public boolean containsKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        
        String lowerKeyword = keyword.toLowerCase().trim();
        
        //            
        if (super.containsKeyword(keyword)) {
            return true;
        }
        
        //             
        if (username != null && username.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //              
        if (email != null && email.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //              
        if (role != null && role.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //              
        if (phoneNumber != null && phoneNumber.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //            
        if (department != null && department.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //       （    "  " "   "）
        if ("  ".equalsIgnoreCase(keyword) && isActive) {
            return true;
        }
        if ("   ".equalsIgnoreCase(keyword) && !isActive) {
            return true;
        }
        if ("active".equalsIgnoreCase(keyword) && isActive) {
            return true;
        }
        if ("inactive".equalsIgnoreCase(keyword) && !isActive) {
            return true;
        }
        
        return false;
    }
    
    /**
     *            
     *       ，          
     * 
     * @return           
     */
    @Override
    public String getDisplayInfo() {
        String status = isActive ? "  " : "   ";
        return name + " (" + username + ") -   : " + role + 
               ",   : " + email + ",   : " + status + 
               (department != null && !department.isEmpty() ? ",   : " + department : "");
    }
    
    /**
     *          
     *       ，         
     * 
     * @return           
     */
    @Override
    public String getDetailedInfo() {
        return getType() + " ID: " + id + 
               ",   : " + name + 
               ",   : " + description + 
               ",    : " + username + 
               ",   : " + email + 
               ",   : " + role + 
               ",   : " + phoneNumber + 
               ",   : " + (isActive ? "  " : "   ") + 
               ",   : " + department;
    }
    
    /**
     *         
     * 
     * @return          
     */
    public String getStatusDescription() {
        return isActive ? "     ，        " : "     ，            ";
    }
    
    // Getter Setter  
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
}