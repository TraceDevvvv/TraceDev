import java.util.Objects;

/**
 *           
 *          ：ID、  、     
 */
public class TouristSite {
    private String siteId;
    private String name;
    private String description;
    private String location;
    
    /**
     *     ，        
     * @param siteId        
     * @param name     
     * @param description     
     * @param location     
     */
    public TouristSite(String siteId, String name, String description, String location) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.location = location;
    }
    
    /**
     *     ID
     * @return   ID
     */
    public String getSiteId() {
        return siteId;
    }
    
    /**
     *     ID
     * @param siteId   ID
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    
    /**
     *       
     * @return     
     */
    public String getName() {
        return name;
    }
    
    /**
     *       
     * @param name     
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *       
     * @return     
     */
    public String getDescription() {
        return description;
    }
    
    /**
     *       
     * @param description     
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     *       
     * @return     
     */
    public String getLocation() {
        return location;
    }
    
    /**
     *       
     * @param location     
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    /**
     *   equals  ，    ID          
     * @param obj       
     * @return     ID     true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TouristSite that = (TouristSite) obj;
        return Objects.equals(siteId, that.siteId);
    }
    
    /**
     *   hashCode  ，    ID     
     * @return    
     */
    @Override
    public int hashCode() {
        return Objects.hash(siteId);
    }
    
    /**
     *   toString  ，          
     * @return         
     */
    @Override
    public String toString() {
        return "TouristSite{" +
                "siteId='" + siteId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}