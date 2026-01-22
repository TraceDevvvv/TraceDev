// Site.java
//        ，             。
//          ， ID、  、   ，   getter setter  。

public class Site {
    //     
    private int id;               //        
    private String name;          //     
    private String description;   //       
    private String location;      //       
    private double rating;        //     （0.0 5.0）
    private int visitCount;       //     
    private boolean isFavorite;   //      
    
    //       
    public Site() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.location = "";
        this.rating = 0.0;
        this.visitCount = 0;
        this.isFavorite = false;
    }
    
    //         
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
        //           
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
        //         
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
    
    //   toString  ，        
    @Override
    public String toString() {
        return "  ID: " + id + "\n" +
               "  : " + name + "\n" +
               "  : " + description + "\n" +
               "  : " + location + "\n" +
               "  : " + String.format("%.1f", rating) + "/5.0\n" +
               "    : " + visitCount + "\n" +
               "   : " + (isFavorite ? " " : " ");
    }
}