package com.example.entitysearch;

/**
 *      
 *           ，   Entity  
 *             ，   、  、   
 */
public class Address extends Entity {
    private String street;           //     
    private String city;             //   
    private String state;            //  /  
    private String postalCode;       //     
    private String country;          //   
    private String buildingName;     //      
    
    /**
     *     
     * 
     * @param id       
     * @param name     
     * @param description     
     * @param street     
     * @param city   
     * @param state  /  
     * @param postalCode     
     * @param country   
     * @param buildingName      
     */
    public Address(String id, String name, String description,
                  String street, String city, String state, 
                  String postalCode, String country, String buildingName) {
        super(id, name, description);
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.buildingName = buildingName;
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
        if (street != null && street.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //            
        if (city != null && city.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //    /         
        if (state != null && state.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //              
        if (postalCode != null && postalCode.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //            
        if (country != null && country.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        //               
        if (buildingName != null && buildingName.toLowerCase().contains(lowerKeyword)) {
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
        StringBuilder display = new StringBuilder();
        display.append(name);
        
        if (buildingName != null && !buildingName.isEmpty()) {
            display.append(" (").append(buildingName).append(")");
        }
        
        display.append(" - ");
        
        //        
        if (street != null && !street.isEmpty()) {
            display.append(street).append(", ");
        }
        
        if (city != null && !city.isEmpty()) {
            display.append(city).append(", ");
        }
        
        if (state != null && !state.isEmpty()) {
            display.append(state).append(", ");
        }
        
        if (postalCode != null && !postalCode.isEmpty()) {
            display.append(postalCode).append(", ");
        }
        
        if (country != null && !country.isEmpty()) {
            display.append(country);
        }
        
        //             
        String result = display.toString().trim();
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        
        return result;
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
               ",     : " + name + 
               ",   : " + description + 
               ",   : " + street + 
               ",   : " + city + 
               ",  /  : " + state + 
               ",     : " + postalCode + 
               ",   : " + country + 
               ",    : " + buildingName;
    }
    
    /**
     *           
     * 
     * @return            
     */
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder();
        
        if (street != null && !street.isEmpty()) {
            fullAddress.append(street).append(", ");
        }
        
        if (city != null && !city.isEmpty()) {
            fullAddress.append(city).append(", ");
        }
        
        if (state != null && !state.isEmpty()) {
            fullAddress.append(state).append(", ");
        }
        
        if (postalCode != null && !postalCode.isEmpty()) {
            fullAddress.append(postalCode).append(", ");
        }
        
        if (country != null && !country.isEmpty()) {
            fullAddress.append(country);
        }
        
        String result = fullAddress.toString().trim();
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        
        return result;
    }
    
    // Getter Setter  
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getBuildingName() {
        return buildingName;
    }
    
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}