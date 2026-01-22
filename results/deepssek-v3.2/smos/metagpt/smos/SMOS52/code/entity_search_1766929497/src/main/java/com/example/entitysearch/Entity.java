package com.example.entitysearch;

/**
 *     
 *                  
 *             
 */
public abstract class Entity {
    protected String id;          //       
    protected String name;        //     
    protected String description; //     
    
    /**
     *     
     * 
     * @param id       
     * @param name     
     * @param description     
     */
    public Entity(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
    /**
     *       
     *                    
     * 
     * @return        
     */
    public abstract String getType();
    
    /**
     *               
     *               
     * 
     * @param keyword        
     * @return            true，    false
     */
    public boolean containsKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        
        String lowerKeyword = keyword.toLowerCase().trim();
        
        //             
        if (name != null && name.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //             
        if (description != null && description.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //              
        return false;
    }
    
    /**
     *            
     *                     
     * 
     * @return         
     */
    public String getDisplayInfo() {
        return name + " - " + description;
    }
    
    /**
     *         
     *                    
     * 
     * @return           
     */
    public String getDetailedInfo() {
        return getType() + " ID: " + id + ",   : " + name + ",   : " + description;
    }
    
    // Getter Setter  
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     *   toString  ，         
     */
    @Override
    public String toString() {
        return getDetailedInfo();
    }
}