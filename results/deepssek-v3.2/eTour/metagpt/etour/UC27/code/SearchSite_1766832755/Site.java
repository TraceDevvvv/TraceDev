/**
 * Site类表示计算机上的一个站点（网站/地址）。
 * 这是一个简单的实体类，包含站点的基本属性。
 */
public class Site {
    private int id;           // 站点唯一标识符
    private String name;      // 站点名称
    private String url;       // 站点URL地址
    private String description; // 站点描述
    private String category;  // 站点分类
    
    /**
     * 默认构造函数
     */
    public Site() {
    }
    
    /**
     * 带参数的构造函数
     * @param id 站点ID
     * @param name 站点名称
     * @param url 站点URL
     * @param description 站点描述
     * @param category 站点分类
     */
    public Site(int id, String name, String url, String description, String category) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.category = category;
    }
    
    // Getter和Setter方法
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * 重写toString方法，便于打印站点信息
     * @return 站点的字符串表示
     */
    @Override
    public String toString() {
        return String.format("Site{id=%d, name='%s', url='%s', description='%s', category='%s'}", 
                            id, name, url, description, category);
    }
    
    /**
     * 判断站点是否为有效的URL格式
     * （简化验证：检查是否以http://或https://开头）
     * @return 如果是有效的URL格式返回true，否则返回false
     */
    public boolean isValidUrl() {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        String trimmedUrl = url.trim().toLowerCase();
        return trimmedUrl.startsWith("http://") || trimmedUrl.startsWith("https://");
    }
    
    /**
     * 判断站点是否匹配指定的搜索关键字
     * @param keyword 搜索关键字
     * @return 如果站点名称、URL、描述或分类包含关键字（不区分大小写）返回true
     */
    public boolean matchesKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        
        String lowerKeyword = keyword.toLowerCase().trim();
        
        // 检查名称、URL、描述或分类是否包含关键字
        return (name != null && name.toLowerCase().contains(lowerKeyword)) ||
               (url != null && url.toLowerCase().contains(lowerKeyword)) ||
               (description != null && description.toLowerCase().contains(lowerKeyword)) ||
               (category != null && category.toLowerCase().contains(lowerKeyword));
    }
}