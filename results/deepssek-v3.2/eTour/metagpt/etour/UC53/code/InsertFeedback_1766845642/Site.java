import java.util.ArrayList;
import java.util.List;

/**
 * Site实体类，表示旅游站点
 * 包含站点ID、名称、描述等属性，以及已访问该站点的游客列表
 */
public class Site {
    private String siteId;
    private String name;
    private String description;
    private List<String> visitedTouristIds; // 已访问该站点的游客ID列表
    
    /**
     * 构造函数
     * @param siteId 站点ID
     * @param name 站点名称
     * @param description 站点描述
     */
    public Site(String siteId, String name, String description) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.visitedTouristIds = new ArrayList<>();
    }
    
    /**
     * 无参构造函数
     */
    public Site() {
        this.visitedTouristIds = new ArrayList<>();
    }
    
    // Getter和Setter方法
    
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
     * 添加游客到已访问列表
     * @param touristId 游客ID
     */
    public void addVisitedTourist(String touristId) {
        if (!visitedTouristIds.contains(touristId)) {
            visitedTouristIds.add(touristId);
        }
    }
    
    /**
     * 检查游客是否已访问过该站点
     * @param touristId 游客ID
     * @return 如果游客已访问过该站点，返回true
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