import java.util.ArrayList;
import java.util.List;

/**
 * Tourist实体类，表示游客
 * 包含游客ID、姓名和已访问站点列表等属性
 */
public class Tourist {
    private String touristId;
    private String name;
    private List<String> visitedSiteIds; // 已访问的站点ID列表
    
    /**
     * 构造函数
     * @param touristId 游客ID
     * @param name 游客姓名
     */
    public Tourist(String touristId, String name) {
        this.touristId = touristId;
        this.name = name;
        this.visitedSiteIds = new ArrayList<>();
    }
    
    /**
     * 无参构造函数
     */
    public Tourist() {
        this.visitedSiteIds = new ArrayList<>();
    }
    
    // Getter和Setter方法
    
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
     * 添加站点到已访问列表
     * @param siteId 站点ID
     */
    public void addVisitedSite(String siteId) {
        if (!visitedSiteIds.contains(siteId)) {
            visitedSiteIds.add(siteId);
        }
    }
    
    /**
     * 检查游客是否已访问过某个站点
     * @param siteId 站点ID
     * @return 如果游客已访问过该站点，返回true
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