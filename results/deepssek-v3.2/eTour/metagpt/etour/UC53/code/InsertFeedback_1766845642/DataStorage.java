import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataStorage  -      
 *        ，    、           
 *             ，                
 */
public class DataStorage {
    //   Map     ，  ID，    
    private Map<String, Feedback> feedbackStorage;
    private Map<String, Site> siteStorage;
    private Map<String, Tourist> touristStorage;
    
    //       ID    
    private int feedbackCounter;
    
    /**
     *     ，       
     */
    public DataStorage() {
        feedbackStorage = new HashMap<>();
        siteStorage = new HashMap<>();
        touristStorage = new HashMap<>();
        feedbackCounter = 1; //  1    
        
        //          
        initializeSampleData();
    }
    
    /**
     *        ，    
     */
    private void initializeSampleData() {
        //       
        Site site1 = new Site("S001", "     ", "         ");
        Site site2 = new Site("S002", "   ", "          ");
        Site site3 = new Site("S003", "  ", "         ");
        
        siteStorage.put(site1.getSiteId(), site1);
        siteStorage.put(site2.getSiteId(), site2);
        siteStorage.put(site3.getSiteId(), site3);
        
        //       
        Tourist tourist1 = new Tourist("T001", "  ");
        Tourist tourist2 = new Tourist("T002", "  ");
        Tourist tourist3 = new Tourist("T003", "  ");
        
        touristStorage.put(tourist1.getTouristId(), tourist1);
        touristStorage.put(tourist2.getTouristId(), tourist2);
        touristStorage.put(tourist3.getTouristId(), tourist3);
    }
    
    /**
     *                   
     * @param touristId   ID
     * @param siteId   ID
     * @return         ，  true；    false
     */
    public boolean hasTouristGivenFeedback(String touristId, String siteId) {
        for (Feedback feedback : feedbackStorage.values()) {
            if (feedback.getTouristId().equals(touristId) && 
                feedback.getSiteId().equals(siteId)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     *        
     * @param feedback         
     * @return       ID
     */
    public String saveFeedback(Feedback feedback) {
        //        ID
        String feedbackId = "F" + String.format("%03d", feedbackCounter++);
        feedback.setFeedbackId(feedbackId);
        
        //        
        feedbackStorage.put(feedbackId, feedback);
        
        //             
        Tourist tourist = touristStorage.get(feedback.getTouristId());
        if (tourist != null) {
            tourist.addVisitedSite(feedback.getSiteId());
        }
        
        //             
        Site site = siteStorage.get(feedback.getSiteId());
        if (site != null) {
            site.addVisitedTourist(feedback.getTouristId());
        }
        
        return feedbackId;
    }
    
    /**
     *     ID   
     * @param siteId   ID
     * @return     ，        null
     */
    public Site getSite(String siteId) {
        return siteStorage.get(siteId);
    }
    
    /**
     *     ID   
     * @param touristId   ID
     * @return     ，        null
     */
    public Tourist getTourist(String touristId) {
        return touristStorage.get(touristId);
    }
    
    /**
     *     ID   
     * @param feedbackId   ID
     * @return     ，        null
     */
    public Feedback getFeedback(String feedbackId) {
        return feedbackStorage.get(feedbackId);
    }
    
    /**
     *       
     * @return     
     */
    public List<Feedback> getAllFeedback() {
        return new ArrayList<>(feedbackStorage.values());
    }
    
    /**
     *            
     * @param touristId   ID
     * @return         
     */
    public List<Feedback> getFeedbackByTourist(String touristId) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : feedbackStorage.values()) {
            if (feedback.getTouristId().equals(touristId)) {
                result.add(feedback);
            }
        }
        return result;
    }
    
    /**
     *            
     * @param siteId   ID
     * @return         
     */
    public List<Feedback> getFeedbackBySite(String siteId) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : feedbackStorage.values()) {
            if (feedback.getSiteId().equals(siteId)) {
                result.add(feedback);
            }
        }
        return result;
    }
    
    /**
     *         
     * @param siteId   ID
     * @return         true，    false
     */
    public boolean siteExists(String siteId) {
        return siteStorage.containsKey(siteId);
    }
    
    /**
     *         
     * @param touristId   ID
     * @return         true，    false
     */
    public boolean touristExists(String touristId) {
        return touristStorage.containsKey(touristId);
    }
    
    /**
     *       
     * @return     
     */
    public List<Site> getAllSites() {
        return new ArrayList<>(siteStorage.values());
    }
    
    /**
     *       
     * @return     
     */
    public List<Tourist> getAllTourists() {
        return new ArrayList<>(touristStorage.values());
    }
    
    /**
     *       （      ）
     */
    public void clearAllStorage() {
        feedbackStorage.clear();
        siteStorage.clear();
        touristStorage.clear();
        feedbackCounter = 1;
        initializeSampleData();
    }
    
    /**
     *         
     * @return             
     */
    public String getStorageStats() {
        StringBuilder stats = new StringBuilder();
        stats.append("===        ===\n");
        stats.append("    : ").append(siteStorage.size()).append("\n");
        stats.append("    : ").append(touristStorage.size()).append("\n");
        stats.append("    : ").append(feedbackStorage.size()).append("\n");
        
        //            
        stats.append("\n       :\n");
        for (Site site : siteStorage.values()) {
            List<Feedback> siteFeedback = getFeedbackBySite(site.getSiteId());
            stats.append(String.format("  %s (%s): %d    \n", 
                site.getName(), site.getSiteId(), siteFeedback.size()));
        }
        
        return stats.toString();
    }
}