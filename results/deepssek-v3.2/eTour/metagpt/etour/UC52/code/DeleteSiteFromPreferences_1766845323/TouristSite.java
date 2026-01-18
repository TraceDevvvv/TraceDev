import java.util.Objects;

/**
 * 表示一个旅游站点的类
 * 包含站点的基本信息：ID、名称、描述和位置
 */
public class TouristSite {
    private String siteId;
    private String name;
    private String description;
    private String location;
    
    /**
     * 构造方法，创建旅游站点对象
     * @param siteId 站点唯一标识符
     * @param name 站点名称
     * @param description 站点描述
     * @param location 站点位置
     */
    public TouristSite(String siteId, String name, String description, String location) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.location = location;
    }
    
    /**
     * 获取站点ID
     * @return 站点ID
     */
    public String getSiteId() {
        return siteId;
    }
    
    /**
     * 设置站点ID
     * @param siteId 站点ID
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    
    /**
     * 获取站点名称
     * @return 站点名称
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置站点名称
     * @param name 站点名称
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 获取站点描述
     * @return 站点描述
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 设置站点描述
     * @param description 站点描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 获取站点位置
     * @return 站点位置
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * 设置站点位置
     * @param location 站点位置
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    /**
     * 重写equals方法，基于站点ID判断两个站点是否相同
     * @param obj 要比较的对象
     * @return 如果站点ID相同则返回true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TouristSite that = (TouristSite) obj;
        return Objects.equals(siteId, that.siteId);
    }
    
    /**
     * 重写hashCode方法，基于站点ID生成哈希值
     * @return 哈希值
     */
    @Override
    public int hashCode() {
        return Objects.hash(siteId);
    }
    
    /**
     * 重写toString方法，返回站点的字符串表示
     * @return 站点信息的字符串
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