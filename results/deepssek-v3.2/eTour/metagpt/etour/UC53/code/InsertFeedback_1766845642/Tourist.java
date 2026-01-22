import java.util.ArrayList;
import java.util.List;

/**
 * Tourist   ，    
 *     ID、             
 */
public class Tourist {
    private String touristId;
    private String name;
    private List<String> visitedSiteIds; //       ID  
    
    /**
     *     
     * @param touristId   ID
     * @param name     
     */
    public Tourist(String touristId, String name) {
        this.touristId = touristId;
        this.name = name;
        this.visitedSiteIds = new ArrayList<>();
    }
    
    /**
     *       
     */
    public Tourist() {
        this.visitedSiteIds = new ArrayList<>();
    }
    
    // Getter Setter  
    
    public String getTouristId() {
        return touristId;
    }
    
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> getVisitedSiteIds() {
        return visitedSiteIds;
    }
    
    public void setVisitedSiteIds(List<String> visitedSiteIds) {
        this.visitedSiteIds = visitedSiteIds;
    }
    
    /**
     *           
     * @param siteId   ID
     */
    public void addVisitedSite(String siteId) {
        if (!visitedSiteIds.contains(siteId)) {
            visitedSiteIds.add(siteId);
        }
    }
    
    /**
     *               
     * @param siteId   ID
     * @return            ，  true
     */
    public boolean hasVisitedSite(String siteId) {
        return visitedSiteIds.contains(siteId);
    }
    
    @Override
    public String toString() {
        return "Tourist{" +
                "touristId='" + touristId + '\'' +
                ", name='" + name + '\'' +
                ", visitedSiteIds=" + visitedSiteIds +
                '}';
    }
}