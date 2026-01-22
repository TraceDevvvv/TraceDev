package com.example.entitysearch;

import java.util.ArrayList;
import java.util.List;

/**
 *      
 *           （  、  、  、  ）     
 *       、         
 */
public class SearchService {
    
    private MockData mockData; //       ，        
    
    /**
     *     
     *        
     */
    public SearchService() {
        this.mockData = new MockData();
        System.out.println("        ，      ...");
    }
    
    /**
     *         
     *                ，         
     * 
     * @param keyword      
     * @return             
     */
    public List<Entity> searchAllEntities(String keyword) {
        List<Entity> allResults = new ArrayList<>();
        
        //         
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("  ：       ，     ");
            return allResults;
        }
        
        System.out.println("       : \"" + keyword + "\"");
        
        //            
        System.out.println("      ...");
        List<ClassEntity> classResults = searchClassEntities(keyword);
        allResults.addAll(classResults);
        
        System.out.println("      ...");
        List<Teaching> teachingResults = searchTeachingEntities(keyword);
        allResults.addAll(teachingResults);
        
        System.out.println("      ...");
        List<Address> addressResults = searchAddressEntities(keyword);
        allResults.addAll(addressResults);
        
        System.out.println("      ...");
        List<User> userResults = searchUserEntities(keyword);
        allResults.addAll(userResults);
        
        System.out.println("    ，    " + allResults.size() + "       ");
        
        return allResults;
    }
    
    /**
     *       
     *                 
     * 
     * @param keyword      
     * @return          
     */
    public List<ClassEntity> searchClassEntities(String keyword) {
        List<ClassEntity> results = new ArrayList<>();
        List<ClassEntity> allClasses = mockData.getAllClassEntities();
        
        for (ClassEntity classEntity : allClasses) {
            if (classEntity.containsKeyword(keyword)) {
                results.add(classEntity);
            }
        }
        
        return results;
    }
    
    /**
     *       
     *                 
     * 
     * @param keyword      
     * @return          
     */
    public List<Teaching> searchTeachingEntities(String keyword) {
        List<Teaching> results = new ArrayList<>();
        List<Teaching> allTeachings = mockData.getAllTeachingEntities();
        
        for (Teaching teaching : allTeachings) {
            if (teaching.containsKeyword(keyword)) {
                results.add(teaching);
            }
        }
        
        return results;
    }
    
    /**
     *       
     *                 
     * 
     * @param keyword      
     * @return          
     */
    public List<Address> searchAddressEntities(String keyword) {
        List<Address> results = new ArrayList<>();
        List<Address> allAddresses = mockData.getAllAddressEntities();
        
        for (Address address : allAddresses) {
            if (address.containsKeyword(keyword)) {
                results.add(address);
            }
        }
        
        return results;
    }
    
    /**
     *       
     *                 
     * 
     * @param keyword      
     * @return          
     */
    public List<User> searchUserEntities(String keyword) {
        List<User> results = new ArrayList<>();
        List<User> allUsers = mockData.getAllUserEntities();
        
        for (User user : allUsers) {
            if (user.containsKeyword(keyword)) {
                results.add(user);
            }
        }
        
        return results;
    }
    
    /**
     *           
     *             
     * 
     * @param keyword      
     * @return           SearchResult  
     */
    public SearchResult searchEntitiesGrouped(String keyword) {
        List<ClassEntity> classes = searchClassEntities(keyword);
        List<Teaching> teachings = searchTeachingEntities(keyword);
        List<Address> addresses = searchAddressEntities(keyword);
        List<User> users = searchUserEntities(keyword);
        
        return new SearchResult(keyword, classes, teachings, addresses, users);
    }
    
    /**
     *      -         
     *            
     * 
     * @param keyword      
     * @param adminId         ID
     * @return          ID
     */
    public AdvancedSearchResult advancedSearch(String keyword, String adminId) {
        long startTime = System.currentTimeMillis();
        
        //     
        List<Entity> searchResults = searchAllEntities(keyword);
        
        long endTime = System.currentTimeMillis();
        long searchDuration = endTime - startTime;
        
        //         ID
        String searchId = generateSearchId(keyword, adminId);
        
        System.out.println("       -   ID: " + searchId + 
                          ",   : " + searchDuration + "ms");
        
        return new AdvancedSearchResult(searchId, keyword, searchResults, searchDuration);
    }
    
    /**
     *     ID
     *      、   ID          ID
     * 
     * @param keyword      
     * @param adminId    ID
     * @return      ID
     */
    private String generateSearchId(String keyword, String adminId) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String keywordHash = String.valueOf(Math.abs(keyword.hashCode() % 10000));
        
        return "SRCH-" + adminId + "-" + keywordHash + "-" + timestamp.substring(timestamp.length() - 6);
    }
    
    /**
     *       
     *               
     * 
     * @param adminId    ID
     * @return        true，    false
     */
    public boolean validateSearchPermission(String adminId) {
        //         
        if (adminId == null || adminId.isEmpty()) {
            return false;
        }
        
        //       ，             
        return adminId.startsWith("ADMIN-");
    }
    
    /**
     *       
     *                     
     */
    public void clearSearchCache() {
        System.out.println("       ");
        //       ，            
    }
    
    /**
     *    ：       
     *               
     */
    public static class SearchResult {
        private final String keyword;
        private final List<ClassEntity> classes;
        private final List<Teaching> teachings;
        private final List<Address> addresses;
        private final List<User> users;
        
        public SearchResult(String keyword, List<ClassEntity> classes, 
                          List<Teaching> teachings, List<Address> addresses, 
                          List<User> users) {
            this.keyword = keyword;
            this.classes = classes != null ? classes : new ArrayList<>();
            this.teachings = teachings != null ? teachings : new ArrayList<>();
            this.addresses = addresses != null ? addresses : new ArrayList<>();
            this.users = users != null ? users : new ArrayList<>();
        }
        
        public String getKeyword() { return keyword; }
        public List<ClassEntity> getClasses() { return classes; }
        public List<Teaching> getTeachings() { return teachings; }
        public List<Address> getAddresses() { return addresses; }
        public List<User> getUsers() { return users; }
        
        public int getTotalCount() {
            return classes.size() + teachings.size() + addresses.size() + users.size();
        }
        
        @Override
        public String toString() {
            return "     : \"" + keyword + "\", " +
                   "  : " + classes.size() + ", " +
                   "  : " + teachings.size() + ", " +
                   "  : " + addresses.size() + ", " +
                   "  : " + users.size() + ", " +
                   "  : " + getTotalCount();
        }
    }
    
    /**
     *    ：      
     *        ，   ID     
     */
    public static class AdvancedSearchResult {
        private final String searchId;
        private final String keyword;
        private final List<Entity> results;
        private final long searchDuration;
        
        public AdvancedSearchResult(String searchId, String keyword, 
                                   List<Entity> results, long searchDuration) {
            this.searchId = searchId;
            this.keyword = keyword;
            this.results = results != null ? results : new ArrayList<>();
            this.searchDuration = searchDuration;
        }
        
        public String getSearchId() { return searchId; }
        public String getKeyword() { return keyword; }
        public List<Entity> getResults() { return results; }
        public long getSearchDuration() { return searchDuration; }
        
        @Override
        public String toString() {
            return "  ID: " + searchId + 
                   ",    : \"" + keyword + "\", " +
                   "   : " + results.size() + ", " +
                   "    : " + searchDuration + "ms";
        }
    }
}