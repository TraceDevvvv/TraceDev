import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 反馈查看系统 - 允许机构操作员查看特定站点的所有反馈
 * 
 * 系统设计符合以下要求：
 * 1. 机构操作员必须已登录才能使用系统
 * 2. 从SearchSite用例获取站点列表
 * 3. 选择特定站点后查看该站点的所有反馈
 * 4. 处理服务器连接中断的情况
 * 5. 完全可运行的Java程序
 */

/**
 * 反馈实体类 - 表示一个站点的反馈
 */
class Feedback {
    private int id;
    private String comment;
    private String author;
    private LocalDateTime timestamp;
    private int rating; // 1-5星评分
    
    public Feedback(int id, String comment, String author, LocalDateTime timestamp, int rating) {
        this.id = id;
        this.comment = comment;
        this.author = author;
        this.timestamp = timestamp;
        this.rating = rating;
    }
    
    public int getId() { return id; }
    public String getComment() { return comment; }
    public String getAuthor() { return author; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getRating() { return rating; }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String stars = "★".repeat(rating) + "☆".repeat(5 - rating);
        return String.format("[%s] %s\n作者: %s\n时间: %s\n评分: %s\n",
                stars, comment, author, timestamp.format(formatter), stars);
    }
}

/**
 * 站点实体类 - 表示一个可以接收反馈的站点
 */
class Site {
    private int id;
    private String name;
    private String description;
    private List<Feedback> feedbacks;
    
    public Site(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.feedbacks = new ArrayList<>();
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Feedback> getFeedbacks() { return feedbacks; }
    
    // 添加反馈到站点
    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }
    
    // 获取站点的所有反馈
    public List<Feedback> getAllFeedbacks() {
        return new ArrayList<>(feedbacks);
    }
    
    @Override
    public String toString() {
        return String.format("站点ID: %d\n名称: %s\n描述: %s\n反馈数量: %d\n",
                id, name, description, feedbacks.size());
    }
}

/**
 * 服务器模拟器 - 模拟从服务器获取数据，包含连接处理
 */
class ServerSimulator {
    /**
     * 模拟从服务器获取站点列表 - 这通常来自SearchSite用例
     * 在实际应用中，这里会有HTTP请求到服务器API
     * 
     * @return 站点列表
     * @throws IOException 模拟服务器连接中断的情况
     */
    public static List<Site> getSiteListFromServer() throws IOException {
        // 模拟服务器连接检查
        if (!isServerAvailable()) {
            throw new IOException("服务器连接中断，无法获取站点列表");
        }
        
        List<Site> sites = new ArrayList<>();
        
        // 创建模拟站点数据
        sites.add(new Site(1, "北京分公司", "位于北京市朝阳区的分公司"));
        sites.add(new Site(2, "上海分公司", "位于上海市浦东新区的分公司"));
        sites.add(new Site(3, "广州分公司", "位于广州市天河区的分公司"));
        sites.add(new Site(4, "深圳分公司", "位于深圳市南山区的分公司"));
        sites.add(new Site(5, "成都分公司", "位于成都市锦江区的分公司"));
        
        // 为每个站点添加模拟反馈数据
        addDummyFeedbacks(sites);
        
        return sites;
    }
    
    /**
     * 模拟获取特定站点的反馈
     * 
     * @param siteId 站点ID
     * @return 指定站点的反馈列表
     * @throws IOException 模拟服务器连接中断的情况
     */
    public static List<Feedback> getFeedbackForSite(int siteId) throws IOException {
        // 模拟服务器连接检查
        if (!isServerAvailable()) {
            throw new IOException("服务器连接中断，无法获取反馈数据");
        }
        
        List<Site> allSites = new ArrayList<>();
        addDummyFeedbacks(allSites);
        
        // 在实际应用中，这里会有数据库查询或API调用
        for (Site site : allSites) {
            if (site.getId() == siteId) {
                return site.getAllFeedbacks();
            }
        }
        
        return new ArrayList<>(); // 如果没有找到站点，返回空列表
    }
    
    /**
     * 检查服务器是否可用
     * 
     * @return true如果服务器可用，false如果不可用
     */
    public static boolean isServerAvailable() {
        // 模拟10%的失败率来测试连接中断处理
        return Math.random() > 0.1;
    }
    
    /**
     * 添加模拟反馈数据到站点
     */
    private static void addDummyFeedbacks(List<Site> sites) {
        if (sites.isEmpty()) {
            // 如果没有站点，先创建一些
            sites.add(new Site(1, "北京分公司", "位于北京市朝阳区的分公司"));
            sites.add(new Site(2, "上海分公司", "位于上海市浦东新区的分公司"));
        }
        
        for (Site site : sites) {
            // 为每个站点添加3个模拟反馈
            site.addFeedback(new Feedback(1, 
                site.getName() + "的服务非常专业，响应迅速。", 
                "张经理", 
                LocalDateTime.now().minusDays(3), 
                5));
            
            site.addFeedback(new Feedback(2, 
                "设备维护及时，但网络有时不稳定。", 
                "李主管", 
                LocalDateTime.now().minusDays(7), 
                3));
            
            site.addFeedback(new Feedback(3, 
                "员工培训到位，客户满意度高。", 
                "王总监", 
                LocalDateTime.now().minusDays(1), 
                4));
        }
    }
}

/**
 * 用户会话管理 - 处理用户登录状态
 */
class UserSession {
    private boolean isLoggedIn = false;
    private String username;
    
    /**
     * 用户登录
     * 
     * @param username 用户名
     * @param password 密码
     * @return true如果登录成功，false如果失败
     */
    public boolean login(String username, String password) {
        // 简单的模拟登录检查
        if ("agency_operator".equals(username) && "password123".equals(password)) {
            this.isLoggedIn = true;
            this.username = username;
            System.out.println("登录成功！欢迎 " + username);
            return true;
        } else {
            System.out.println("登录失败：用户名或密码不正确");
            return false;
        }
    }
    
    /**
     * 用户登出
     */
    public void logout() {
        this.isLoggedIn = false;
        this.username = null;
        System.out.println("已登出系统");
    }
    
    /**
     * 检查用户是否已登录
     * 
     * @return true如果用户已登录
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * 获取当前登录的用户名
     * 
     * @return 用户名，如果未登录返回null
     */
    public String getUsername() {
        return username;
    }
}

/**
 * 反馈查看器主类 - 实现ViewFeedback用例的主要功能
 */
public class FeedbackViewer {
    private UserSession userSession;
    private Scanner scanner;
    
    public FeedbackViewer() {
        this.userSession = new UserSession();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 主程序入口点
     */
    public void start() {
        System.out.println("=== 站点反馈查看系统 ===\n");
        
        // 步骤1：用户登录
        if (!performLogin()) {
            System.out.println("登录失败，程序退出。");
            return;
        }
        
        boolean continueRunning = true;
        
        while (continueRunning && userSession.isLoggedIn()) {
            try {
                // 步骤2：显示主菜单
                displayMainMenu();
                
                int choice = getMenuChoice(1, 3);
                
                switch (choice) {
                    case 1:
                        viewSiteFeedbacks();
                        break;
                    case 2:
                        searchAndViewSite();
                        break;
                    case 3:
                        userSession.logout();
                        continueRunning = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("发生错误: " + e.getMessage());
                System.out.println("返回主菜单...\n");
            }
        }
        
        System.out.println("感谢使用站点反馈查看系统！");
        scanner.close();
    }
    
    /**
     * 执行用户登录流程
     * 
     * @return true如果登录成功
     */
    private boolean performLogin() {
        System.out.println("请先登录系统");
        System.out.print("用户名: ");
        String username = scanner.nextLine();
        System.out.print("密码: ");
        String password = scanner.nextLine();
        
        return userSession.login(username, password);
    }
    
    /**
     * 显示主菜单
     */
    private void displayMainMenu() {
        System.out.println("\n===== 主菜单 =====");
        System.out.println("1. 查看站点反馈");
        System.out.println("2. 搜索并查看站点反馈");
        System.out.println("3. 退出系统");
        System.out.print("请选择操作 (1-3): ");
    }
    
    /**
     * 获取用户在菜单中的选择
     * 
     * @param min 最小有效选择
     * @param max 最大有效选择
     * @return 用户的选择
     */
    private int getMenuChoice(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);
                
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("请输入有效的选项 (" + min + "-" + max + "): ");
                }
            } catch (NumberFormatException e) {
                System.out.print("请输入数字 (" + min + "-" + max + "): ");
            }
        }
    }
    
    /**
     * 查看站点反馈 - 主要业务逻辑
     * 对应用例步骤：选择并激活查看反馈的功能
     */
    private void viewSiteFeedbacks() {
        System.out.println("\n===== 查看站点反馈 =====");
        
        try {
            // 从服务器获取站点列表（模拟SearchSite用例）
            System.out.println("正在从服务器获取站点列表...");
            List<Site> sites = ServerSimulator.getSiteListFromServer();
            
            // 显示站点列表供用户选择
            displaySiteList(sites);
            
            if (sites.isEmpty()) {
                System.out.println("没有找到任何站点。");
                return;
            }
            
            System.out.print("请输入要查看反馈的站点编号 (0返回): ");
            int siteChoice = getMenuChoice(0, sites.size());
            
            if (siteChoice == 0) {
                return; // 用户选择返回
            }
            
            Site selectedSite = sites.get(siteChoice - 1);
            System.out.println("\n=== " + selectedSite.getName() + " 的反馈 ===");
            System.out.println(selectedSite);
            
            // 获取并显示该站点的所有反馈
            displayFeedbackForSite(selectedSite.getId());
            
        } catch (IOException e) {
            // 处理服务器连接中断的情况
            System.out.println("错误: " + e.getMessage());
            System.out.println("请检查网络连接后重试。");
        }
    }
    
    /**
     * 搜索并查看站点反馈 - 扩展功能
     */
    private void searchAndViewSite() {
        System.out.println("\n===== 搜索并查看站点反馈 =====");
        
        try {
            System.out.print("请输入要搜索的站点名称或关键词: ");
            String searchTerm = scanner.nextLine().toLowerCase();
            
            List<Site> allSites = ServerSimulator.getSiteListFromServer();
            List<Site> filteredSites = new ArrayList<>();
            
            // 根据关键词过滤站点
            for (Site site : allSites) {
                if (site.getName().toLowerCase().contains(searchTerm) ||
                    site.getDescription().toLowerCase().contains(searchTerm)) {
                    filteredSites.add(site);
                }
            }
            
            if (filteredSites.isEmpty()) {
                System.out.println("没有找到匹配的站点。");
                return;
            }
            
            displaySiteList(filteredSites);
            
            System.out.print("请输入要查看反馈的站点编号 (0返回): ");
            int siteChoice = getMenuChoice(0, filteredSites.size());
            
            if (siteChoice == 0) {
                return;
            }
            
            Site selectedSite = filteredSites.get(siteChoice - 1);
            System.out.println("\n=== " + selectedSite.getName() + " 的反馈 ===");
            System.out.println(selectedSite);
            
            displayFeedbackForSite(selectedSite.getId());
            
        } catch (IOException e) {
            System.out.println("错误: " + e.getMessage());
            System.out.println("请检查网络连接后重试。");
        }
    }
    
    /**
     * 显示站点列表
     * 
     * @param sites 要显示的站点列表
     */
    private void displaySiteList(List<Site> sites) {
        System.out.println("\n可用的站点列表:");
        System.out.println("=========================================");
        
        for (int i = 0; i < sites.size(); i++) {
            Site site = sites.get(i);
            System.out.printf("%d. %s - %s (反馈数: %d)%n",
                    i + 1,
                    site.getName(),
                    site.getDescription(),
                    site.getFeedbacks().size());
        }
        
        System.out.println("=========================================");
    }
    
    /**
     * 显示指定站点的所有反馈
     * 
     * @param siteId 站点ID
     * @throws IOException 如果无法连接服务器
     */
    private void displayFeedbackForSite(int siteId) throws IOException {
        List<Feedback> feedbacks = ServerSimulator.getFeedbackForSite(siteId);
        
        if (feedbacks.isEmpty()) {
            System.out.println("该站点目前没有任何反馈。");
            return;
        }
        
        System.out.println("反馈详情:");
        System.out.println("=========================================");
        
        for (int i = 0; i < feedbacks.size(); i++) {
            System.out.println("反馈 #" + (i + 1));
            System.out.println(feedbacks.get(i));
            System.out.println("-----------------------------------------");
        }
        
        // 显示统计信息
        displayFeedbackStatistics(feedbacks);
    }
    
    /**
     * 显示反馈统计信息
     * 
     * @param feedbacks 反馈列表
     */
    private void displayFeedbackStatistics(List<Feedback> feedbacks) {
        if (feedbacks.isEmpty()) {
            return;
        }
        
        int totalRating = 0;
        for (Feedback feedback : feedbacks) {
            totalRating += feedback.getRating();
        }
        
        double averageRating = (double) totalRating / feedbacks.size();
        
        System.out.println("统计信息:");
        System.out.println("反馈总数: " + feedbacks.size());
        System.out.printf("平均评分: %.1f/5.0%n", averageRating);
        
        // 评分分布
        int[] ratingCount = new int[6]; // 索引0不使用，1-5对应评分
        for (Feedback feedback : feedbacks) {
            ratingCount[feedback.getRating()]++;
        }
        
        System.out.println("评分分布:");
        for (int i = 5; i >= 1; i--) {
            System.out.printf("%d星: %d个反馈%n", i, ratingCount[i]);
        }
        
        System.out.println("=========================================\n");
        
        // 提供后续操作选项
        System.out.println("操作选项:");
        System.out.println("1. 返回主菜单");
        System.out.println("2. 查看其他站点");
        System.out.print("请选择: ");
        
        int choice = getMenuChoice(1, 2);
        
        if (choice == 2) {
            viewSiteFeedbacks();
        }
    }
    
    /**
     * 应用程序的主方法
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        FeedbackViewer feedbackViewer = new FeedbackViewer();
        feedbackViewer.start();
    }
}