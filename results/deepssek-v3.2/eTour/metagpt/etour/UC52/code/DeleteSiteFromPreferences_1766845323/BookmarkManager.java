import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *           
 *     、  、         
 *    ETOUR           
 */
public class BookmarkManager {
    private List<TouristSite> bookmarkedSites;
    private boolean isConnectedToETour;
    
    /**
     *     ，               
     */
    public BookmarkManager() {
        this.bookmarkedSites = new ArrayList<>();
        this.isConnectedToETour = true; //       
    }
    
    /**
     *          
     * @param site         
     * @throws ETourConnectionException    ETOUR        
     */
    public void addSiteToBookmarks(TouristSite site) throws ETourConnectionException {
        //        
        checkConnection();
        
        //          
        if (!bookmarkedSites.contains(site)) {
            bookmarkedSites.add(site);
            System.out.println("   '" + site.getName() + "'         ");
        } else {
            System.out.println("   '" + site.getName() + "'        ");
        }
    }
    
    /**
     *             
     * @param siteId       ID
     * @return         true，    false
     * @throws ETourConnectionException    ETOUR        
     */
    public boolean deleteSiteFromBookmarks(String siteId) throws ETourConnectionException {
        //        
        checkConnection();
        
        //         
        Optional<TouristSite> siteToRemove = bookmarkedSites.stream()
                .filter(site -> site.getSiteId().equals(siteId))
                .findFirst();
        
        if (siteToRemove.isPresent()) {
            TouristSite removedSite = siteToRemove.get();
            bookmarkedSites.remove(removedSite);
            System.out.println("   '" + removedSite.getName() + "'          ");
            return true;
        } else {
            System.out.println("   ID  '" + siteId + "'    ");
            return false;
        }
    }
    
    /**
     *         
     * @return          
     * @throws ETourConnectionException    ETOUR        
     */
    public List<TouristSite> getAllBookmarkedSites() throws ETourConnectionException {
        //        
        checkConnection();
        
        //               
        return new ArrayList<>(bookmarkedSites);
    }
    
    /**
     *     ID      
     * @param siteId       ID
     * @return          ，    null
     * @throws ETourConnectionException    ETOUR        
     */
    public TouristSite findSiteById(String siteId) throws ETourConnectionException {
        //        
        checkConnection();
        
        return bookmarkedSites.stream()
                .filter(site -> site.getSiteId().equals(siteId))
                .findFirst()
                .orElse(null);
    }
    
    /**
     *       
     * @return     
     * @throws ETourConnectionException    ETOUR        
     */
    public int getBookmarkCount() throws ETourConnectionException {
        //        
        checkConnection();
        
        return bookmarkedSites.size();
    }
    
    /**
     *           
     * @param siteId       ID
     * @return        true
     * @throws ETourConnectionException    ETOUR        
     */
    public boolean containsSite(String siteId) throws ETourConnectionException {
        //        
        checkConnection();
        
        return bookmarkedSites.stream()
                .anyMatch(site -> site.getSiteId().equals(siteId));
    }
    
    /**
     *    ETOUR        
     * @throws ETourConnectionException       
     */
    private void checkConnection() throws ETourConnectionException {
        if (!isConnectedToETour) {
            throw new ETourConnectionException(" ETOUR         ");
        }
    }
    
    /**
     *          
     * @param connected     ，true      ，false      
     */
    public void setConnectionStatus(boolean connected) {
        this.isConnectedToETour = connected;
        if (!connected) {
            System.out.println("  ： ETOUR         ");
        } else {
            System.out.println("  ： ETOUR         ");
        }
    }
    
    /**
     *          
     * @return     
     */
    public boolean isConnectedToETour() {
        return isConnectedToETour;
    }
    
    /**
     *       
     * @throws ETourConnectionException    ETOUR        
     */
    public void clearAllBookmarks() throws ETourConnectionException {
        //        
        checkConnection();
        
        int count = bookmarkedSites.size();
        bookmarkedSites.clear();
        System.out.println("       ，    " + count + "    ");
    }
    
    /**
     *         
     * @throws ETourConnectionException    ETOUR        
     */
    public void displayAllBookmarks() throws ETourConnectionException {
        //        
        checkConnection();
        
        if (bookmarkedSites.isEmpty()) {
            System.out.println("      ");
        } else {
            System.out.println("      （  " + bookmarkedSites.size() + "    ）：");
            for (int i = 0; i < bookmarkedSites.size(); i++) {
                TouristSite site = bookmarkedSites.get(i);
                System.out.println((i + 1) + ". ID: " + site.getSiteId() + 
                                 ",   : " + site.getName() + 
                                 ",   : " + site.getLocation());
            }
        }
    }
}