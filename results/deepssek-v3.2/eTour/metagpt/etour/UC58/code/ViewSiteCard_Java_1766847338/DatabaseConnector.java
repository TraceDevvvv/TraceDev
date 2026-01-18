// DatabaseConnector.java
// 数据库连接类，模拟数据库连接和查询，处理连接异常。
// 由于这是一个示例，我们不使用真实数据库，而是用内存数据模拟。

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DatabaseConnector {
    // 模拟数据库中的数据表，键为站点ID，值为Site对象
    private static Map<Integer, Site> siteDatabase = new HashMap<>();
    
    // 随机数生成器，用于模拟连接失败
    private static Random random = new Random();
    
    // 数据库连接状态
    private boolean isConnected;
    
    // 模拟数据库连接URL、用户名和密码（实际应用中应从配置读取）
    private String url;
    private String username;
    private String password;
    
    // 静态初始化块，填充模拟数据
    static {
        // 添加一些示例站点数据
        siteDatabase.put(1, new Site(1, "埃菲尔铁塔", "位于法国巴黎的标志性铁塔，高324米。", "巴黎，法国", 4.8, 10000, true));
        siteDatabase.put(2, new Site(2, "长城", "中国古代的军事防御工程，世界文化遗产。", "北京，中国", 4.9, 15000, true));
        siteDatabase.put(3, new Site(3, "自由女神像", "位于美国纽约的自由象征雕像。", "纽约，美国", 4.7, 8000, false));
        siteDatabase.put(4, new Site(4, "金字塔", "古埃及法老的陵墓，世界七大奇迹之一。", "开罗，埃及", 4.6, 6000, false));
        siteDatabase.put(5, new Site(5, "悉尼歌剧院", "位于澳大利亚悉尼的表演艺术中心。", "悉尼，澳大利亚", 4.5, 7000, true));
    }
    
    // 构造函数，初始化连接参数
    public DatabaseConnector(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.isConnected = false;
    }
    
    // 默认构造函数，使用默认连接参数（适用于示例）
    public DatabaseConnector() {
        this("jdbc:mysql://localhost:3306/etour_db", "tourist_user", "password123");
    }
    
    // 连接到数据库（模拟）
    public void connect() throws DatabaseConnectionException {
        // 模拟连接过程：有20%的概率连接失败，模拟服务器连接中断
        if (random.nextDouble() < 0.2) {
            throw new DatabaseConnectionException("连接ETOUR服务器失败：服务器连接中断。请检查网络连接并重试。");
        }
        
        // 模拟连接成功
        System.out.println("正在连接到数据库: " + url);
        // 实际应用中这里会有JDBC连接代码
        // 例如：Connection conn = DriverManager.getConnection(url, username, password);
        
        // 模拟连接延迟
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        isConnected = true;
        System.out.println("数据库连接成功！");
    }
    
    // 断开数据库连接（模拟）
    public void disconnect() {
        if (isConnected) {
            System.out.println("正在断开数据库连接...");
            isConnected = false;
            System.out.println("数据库连接已关闭。");
        }
    }
    
    // 查询站点信息（模拟）
    public Site getSiteById(int siteId) throws DatabaseConnectionException {
        // 检查是否已连接
        if (!isConnected) {
            throw new DatabaseConnectionException("数据库未连接。请先调用connect()方法建立连接。");
        }
        
        // 模拟查询过程：有10%的概率查询失败，模拟查询过程中连接中断
        if (random.nextDouble() < 0.1) {
            throw new DatabaseConnectionException("查询过程中连接中断。请重试。");
        }
        
        // 模拟查询延迟
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 从模拟数据库中获取站点
        Site site = siteDatabase.get(siteId);
        
        if (site == null) {
            System.out.println("未找到ID为 " + siteId + " 的站点。");
            return null;
        }
        
        System.out.println("成功从数据库获取站点: " + site.getName());
        return site;
    }
    
    // 获取所有站点ID列表（用于模拟用户选择）
    public int[] getAllSiteIds() throws DatabaseConnectionException {
        if (!isConnected) {
            throw new DatabaseConnectionException("数据库未连接。请先调用connect()方法建立连接。");
        }
        
        // 模拟查询延迟
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 返回所有站点ID
        int[] ids = new int[siteDatabase.size()];
        int i = 0;
        for (Integer id : siteDatabase.keySet()) {
            ids[i] = id;
            i++;
        }
        return ids;
    }
    
    // 获取当前连接状态
    public boolean isConnected() {
        return isConnected;
    }
}

// 自定义数据库连接异常类
class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(String message) {
        super(message);
    }
}