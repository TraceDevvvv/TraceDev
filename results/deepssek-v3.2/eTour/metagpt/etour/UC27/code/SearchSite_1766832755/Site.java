/**
 * Site            （  /  ）。
 *           ，         。
 */
public class Site {
    private int id;           //        
    private String name;      //     
    private String url;       //   URL  
    private String description; //     
    private String category;  //     
    
    /**
     *       
     */
    public Site() {
    }
    
    /**
     *         
     * @param id   ID
     * @param name     
     * @param url   URL
     * @param description     
     * @param category     
     */
    public Site(int id, String name, String url, String description, String category) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.category = category;
    }
    
    // Getter Setter  
    
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
     *   toString  ，        
     * @return         
     */
    @Override
    public String toString() {
        return String.format("Site{id=%d, name='%s', url='%s', description='%s', category='%s'}", 
                            id, name, url, description, category);
    }
    
    /**
     *           URL  
     * （    ：     http:// https://  ）
     * @return       URL    true，    false
     */
    public boolean isValidUrl() {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        String trimmedUrl = url.trim().toLowerCase();
        return trimmedUrl.startsWith("http://") || trimmedUrl.startsWith("https://");
    }
    
    /**
     *                 
     * @param keyword      
     * @return       、URL、          （      ）  true
     */
    public boolean matchesKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        
        String lowerKeyword = keyword.toLowerCase().trim();
        
        //     、URL、            
        return (name != null && name.toLowerCase().contains(lowerKeyword)) ||
               (url != null && url.toLowerCase().contains(lowerKeyword)) ||
               (description != null && description.toLowerCase().contains(lowerKeyword)) ||
               (category != null && category.toLowerCase().contains(lowerKeyword));
    }
}