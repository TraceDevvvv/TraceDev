import java.util.ArrayList;
import java.util.List;

/**
 * Site   ，      
 *     ID、  、     ，             
 */
public class Site {
    private String siteId;
    private String name;
    private String description;
    private List<String> visitedTouristIds; //          ID  
    
    /**
     *     
     * @param siteId   ID
     * @param name     
     * @param description     
     */
    public Site(String siteId, String name, String description) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.visitedTouristIds = new ArrayList<>();
    }
    
    /**
     *       
     */
    public Site() {
        this.visitedTouristIds = new ArrayList<>();
    }
    
    // Getter Setter  
    
    public String getSiteId() {
        return siteId;
    }
    
    public void setSiteId(String siteId) {
        this.siteId = siteId;
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
    
    public List<String> getVisitedTouristIds() {
        return visitedTouristIds;
    }
    
    public void setVisitedTouristIds(List<String> visitedTouristIds) {
        this.visitedTouristIds = visitedTouristIds;
    }
    
    /**
     *           
     * @param touristId   ID
     */
    public void addVisitedTourist(String touristId) {
        if (!visitedTouristIds.contains(touristId)) {
            visitedTouristIds.add(touristId);
        }
    }
    
    /**
     *              
     * @param touristId   ID
     * @return            ，  true
     */
    public boolean hasTouristVisited(String touristId) {
        return visitedTouristIds.contains(touristId);
    }
    
    @Override
    public String toString() {
        return "Site{" +
                "siteId='" + siteId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", visitedTouristIds=" + visitedTouristIds +
                '}';
    }
}