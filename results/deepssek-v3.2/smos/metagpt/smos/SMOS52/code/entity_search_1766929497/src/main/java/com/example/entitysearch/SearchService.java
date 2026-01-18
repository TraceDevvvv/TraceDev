package com.example.entitysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索服务类
 * 实现对系统中所有实体（班级、教学、地址、用户）的搜索功能
 * 包含搜索逻辑、结果处理和性能优化
 */
public class SearchService {
    
    private MockData mockData; // 模拟数据实例，用于获取测试数据
    
    /**
     * 构造函数
     * 初始化搜索服务
     */
    public SearchService() {
        this.mockData = new MockData();
        System.out.println("搜索服务已初始化，加载模拟数据...");
    }
    
    /**
     * 搜索所有类型实体
     * 对系统中的所有实体类型执行搜索，返回匹配的实体列表
     * 
     * @param keyword 搜索关键词
     * @return 匹配关键词的所有实体列表
     */
    public List<Entity> searchAllEntities(String keyword) {
        List<Entity> allResults = new ArrayList<>();
        
        // 验证关键词有效性
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("警告：搜索关键词为空，返回空结果");
            return allResults;
        }
        
        System.out.println("开始搜索关键词: \"" + keyword + "\"");
        
        // 对每种实体类型执行搜索
        System.out.println("搜索班级实体...");
        List<ClassEntity> classResults = searchClassEntities(keyword);
        allResults.addAll(classResults);
        
        System.out.println("搜索教学实体...");
        List<Teaching> teachingResults = searchTeachingEntities(keyword);
        allResults.addAll(teachingResults);
        
        System.out.println("搜索地址实体...");
        List<Address> addressResults = searchAddressEntities(keyword);
        allResults.addAll(addressResults);
        
        System.out.println("搜索用户实体...");
        List<User> userResults = searchUserEntities(keyword);
        allResults.addAll(userResults);
        
        System.out.println("搜索完成，共找到 " + allResults.size() + " 个匹配的实体");
        
        return allResults;
    }
    
    /**
     * 搜索班级实体
     * 在班级实体中搜索包含关键词的实体
     * 
     * @param keyword 搜索关键词
     * @return 匹配的班级实体列表
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
     * 搜索教学实体
     * 在教学实体中搜索包含关键词的实体
     * 
     * @param keyword 搜索关键词
     * @return 匹配的教学实体列表
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
     * 搜索地址实体
     * 在地址实体中搜索包含关键词的实体
     * 
     * @param keyword 搜索关键词
     * @return 匹配的地址实体列表
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
     * 搜索用户实体
     * 在用户实体中搜索包含关键词的实体
     * 
     * @param keyword 搜索关键词
     * @return 匹配的用户实体列表
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
     * 按实体类型分组的搜索
     * 返回按类型分组的搜索结果
     * 
     * @param keyword 搜索关键词
     * @return 包含按类型分组结果的SearchResult对象
     */
    public SearchResult searchEntitiesGrouped(String keyword) {
        List<ClassEntity> classes = searchClassEntities(keyword);
        List<Teaching> teachings = searchTeachingEntities(keyword);
        List<Address> addresses = searchAddressEntities(keyword);
        List<User> users = searchUserEntities(keyword);
        
        return new SearchResult(keyword, classes, teachings, addresses, users);
    }
    
    /**
     * 高级搜索 - 包含搜索历史记录
     * 执行搜索并记录搜索历史
     * 
     * @param keyword 搜索关键词
     * @param adminId 执行搜索的管理员ID
     * @return 搜索结果和搜索历史ID
     */
    public AdvancedSearchResult advancedSearch(String keyword, String adminId) {
        long startTime = System.currentTimeMillis();
        
        // 执行搜索
        List<Entity> searchResults = searchAllEntities(keyword);
        
        long endTime = System.currentTimeMillis();
        long searchDuration = endTime - startTime;
        
        // 生成搜索历史记录ID
        String searchId = generateSearchId(keyword, adminId);
        
        System.out.println("高级搜索完成 - 搜索ID: " + searchId + 
                          ", 耗时: " + searchDuration + "ms");
        
        return new AdvancedSearchResult(searchId, keyword, searchResults, searchDuration);
    }
    
    /**
     * 生成搜索ID
     * 基于关键词、管理员ID和时间戳生成唯一搜索ID
     * 
     * @param keyword 搜索关键词
     * @param adminId 管理员ID
     * @return 生成的搜索ID
     */
    private String generateSearchId(String keyword, String adminId) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String keywordHash = String.valueOf(Math.abs(keyword.hashCode() % 10000));
        
        return "SRCH-" + adminId + "-" + keywordHash + "-" + timestamp.substring(timestamp.length() - 6);
    }
    
    /**
     * 验证搜索权限
     * 检查管理员是否有权限执行搜索
     * 
     * @param adminId 管理员ID
     * @return 如果有权限返回true，否则返回false
     */
    public boolean validateSearchPermission(String adminId) {
        // 模拟权限检查逻辑
        if (adminId == null || adminId.isEmpty()) {
            return false;
        }
        
        // 在实际系统中，这里会调用权限服务进行检查
        return adminId.startsWith("ADMIN-");
    }
    
    /**
     * 清理搜索缓存
     * 该方法可以用于清理搜索缓存或重置搜索状态
     */
    public void clearSearchCache() {
        System.out.println("搜索缓存已清理");
        // 在实际系统中，这里会清理缓存或重置状态
    }
    
    /**
     * 内部类：搜索结果包装类
     * 用于返回按类型分组的搜索结果
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
            return "搜索关键词: \"" + keyword + "\", " +
                   "班级: " + classes.size() + ", " +
                   "教学: " + teachings.size() + ", " +
                   "地址: " + addresses.size() + ", " +
                   "用户: " + users.size() + ", " +
                   "总计: " + getTotalCount();
        }
    }
    
    /**
     * 内部类：高级搜索结果
     * 包含搜索元数据，如搜索ID和搜索时长
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
            return "搜索ID: " + searchId + 
                   ", 关键词: \"" + keyword + "\", " +
                   "结果数: " + results.size() + ", " +
                   "搜索耗时: " + searchDuration + "ms";
        }
    }
}