// Site.java
// 站点数据模型类，表示一个旅游地点的详细信息。
// 包含站点的基本属性，如ID、名称、描述等，并提供getter和setter方法。

public class Site {
    // 站点属性
    private int id;               // 站点唯一标识符
    private String name;          // 站点名称
    private String description;   // 站点详细描述
    private String location;      // 站点地理位置
    private double rating;        // 站点评分（0.0到5.0）
    private int visitCount;       // 访问次数
    private boolean isFavorite;   // 是否已收藏
    
    // 无参构造函数
    public Site() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.location = "";
        this.rating = 0.0;
        this.visitCount = 0;
        this.isFavorite = false;
    }
    
    // 带参数的构造函数
    public Site(int id, String name, String description, String location, 
                double rating, int visitCount, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.rating = rating;
        this.visitCount = visitCount;
        this.isFavorite = isFavorite;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        // 确保评分在有效范围内
        if (rating < 0.0) {
            this.rating = 0.0;
        } else if (rating > 5.0) {
            this.rating = 5.0;
        } else {
            this.rating = rating;
        }
    }
    
    public int getVisitCount() {
        return visitCount;
    }
    
    public void setVisitCount(int visitCount) {
        // 确保访问次数非负
        if (visitCount < 0) {
            this.visitCount = 0;
        } else {
            this.visitCount = visitCount;
        }
    }
    
    public boolean isFavorite() {
        return isFavorite;
    }
    
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    
    // 重写toString方法，便于打印站点信息
    @Override
    public String toString() {
        return "站点ID: " + id + "\n" +
               "名称: " + name + "\n" +
               "描述: " + description + "\n" +
               "位置: " + location + "\n" +
               "评分: " + String.format("%.1f", rating) + "/5.0\n" +
               "访问次数: " + visitCount + "\n" +
               "已收藏: " + (isFavorite ? "是" : "否");
    }
}