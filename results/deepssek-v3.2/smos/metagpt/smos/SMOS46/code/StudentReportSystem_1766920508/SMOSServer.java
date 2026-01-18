/**
 * SMOSServer.java
 * SMOS服务器类，模拟与SMOS服务器的连接
 * 处理数据同步、归档、连接状态管理等功能
 * 模拟连接中断等异常情况
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SMOSServer {
    private String serverName;                    // 服务器名称
    private String serverAddress;                 // 服务器地址
    private int serverPort;                       // 服务器端口
    private boolean isConnected;                  // 连接状态
    private boolean isAvailable;                  // 服务器可用状态
    private int connectionTimeout;                // 连接超时时间（毫秒）
    private int maxRetryAttempts;                 // 最大重试次数
    private List<String> connectionLog;           // 连接日志
    private Map<Integer, ReportCard> archivedReports; // 已归档的成绩单
    private Random random;                        // 用于模拟随机故障
    
    /**
     * 默认构造函数
     */
    public SMOSServer() {
        this.serverName = "SMOS主服务器";
        this.serverAddress = "smos.example.com";
        this.serverPort = 8080;
        this.isConnected = false;
        this.isAvailable = true;
        this.connectionTimeout = 5000; // 5秒超时
        this.maxRetryAttempts = 3;
        this.connectionLog = new ArrayList<>();
        this.archivedReports = new HashMap<>();
        this.random = new Random();
        
        log("SMOS服务器对象已创建");
    }
    
    /**
     * 带参数的构造函数
     * @param serverName 服务器名称
     * @param serverAddress 服务器地址
     * @param serverPort 服务器端口
     */
    public SMOSServer(String serverName, String serverAddress, int serverPort) {
        this.serverName = serverName;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.isConnected = false;
        this.isAvailable = true;
        this.connectionTimeout = 5000;
        this.maxRetryAttempts = 3;
        this.connectionLog = new ArrayList<>();
        this.archivedReports = new HashMap<>();
        this.random = new Random();
        
        log("SMOS服务器对象已创建: " + serverName + " (" + serverAddress + ":" + serverPort + ")");
    }
    
    /**
     * 连接到SMOS服务器
     * @return 如果连接成功返回true，否则返回false
     */
    public boolean connect() {
        if (!isAvailable) {
            log("错误：服务器当前不可用");
            return false;
        }
        
        if (isConnected) {
            log("警告：已经连接到服务器");
            return true;
        }
        
        log("正在连接到SMOS服务器: " + serverAddress + ":" + serverPort);
        
        // 模拟连接过程（有10%的概率连接失败）
        if (random.nextInt(100) < 10) {
            log("错误：连接超时，服务器无响应");
            isConnected = false;
            return false;
        }
        
        // 模拟连接建立
        try {
            // 模拟网络延迟
            Thread.sleep(1000);
            
            isConnected = true;
            log("成功连接到SMOS服务器");
            return true;
        } catch (InterruptedException e) {
            log("错误：连接过程中断");
            isConnected = false;
            return false;
        }
    }
    
    /**
     * 断开与SMOS服务器的连接
     * @return 如果断开成功返回true，否则返回false
     */
    public boolean disconnect() {
        if (!isConnected) {
            log("警告：当前未连接到服务器");
            return true;
        }
        
        log("正在断开与SMOS服务器的连接");
        
        try {
            // 模拟断开连接过程
            Thread.sleep(500);
            
            isConnected = false;
            log("已断开与SMOS服务器的连接");
            return true;
        } catch (InterruptedException e) {
            log("错误：断开连接过程中断");
            return false;
        }
    }
    
    /**
     * 检查连接状态
     * @return 如果已连接返回true，否则返回false
     */
    public boolean checkConnection() {
        if (!isAvailable) {
            log("服务器不可用");
            return false;
        }
        
        // 模拟连接检查（有5%的概率发现连接已断开）
        if (isConnected && random.nextInt(100) < 5) {
            log("警告：检测到连接已断开");
            isConnected = false;
        }
        
        return isConnected;
    }
    
    /**
     * 归档成绩单到SMOS服务器
     * @param reportCard 要归档的成绩单
     * @return 如果归档成功返回true，否则返回false
     */
    public boolean archiveReportCard(ReportCard reportCard) {
        if (reportCard == null) {
            log("错误：无法归档空成绩单");
            return false;
        }
        
        if (!checkConnection()) {
            log("错误：无法归档，未连接到SMOS服务器");
            return false;
        }
        
        log("正在归档成绩单: ID=" + reportCard.getId() + ", 学生=" + reportCard.getStudentName());
        
        // 模拟归档过程（有15%的概率归档失败）
        if (random.nextInt(100) < 15) {
            log("错误：归档过程中发生错误，连接可能已中断");
            isConnected = false; // 模拟连接中断
            return false;
        }
        
        try {
            // 模拟归档操作
            Thread.sleep(800);
            
            // 将成绩单添加到已归档列表
            archivedReports.put(reportCard.getId(), reportCard);
            reportCard.setArchived(true);
            
            log("成绩单归档成功: ID=" + reportCard.getId());
            return true;
        } catch (InterruptedException e) {
            log("错误：归档过程中断");
            return false;
        }
    }
    
    /**
     * 从SMOS服务器获取已归档的成绩单
     * @param reportCardId 成绩单ID
     * @return 成绩单对象，如果未找到则返回null
     */
    public ReportCard getArchivedReportCard(int reportCardId) {
        if (!checkConnection()) {
            log("错误：无法获取归档成绩单，未连接到SMOS服务器");
            return null;
        }
        
        log("正在从SMOS服务器获取归档成绩单: ID=" + reportCardId);
        
        // 模拟获取过程（有5%的概率失败）
        if (random.nextInt(100) < 5) {
            log("错误：获取归档成绩单失败，连接可能已中断");
            return null;
        }
        
        try {
            // 模拟网络延迟
            Thread.sleep(300);
            
            ReportCard reportCard = archivedReports.get(reportCardId);
            if (reportCard != null) {
                log("成功获取归档成绩单: ID=" + reportCardId);
            } else {
                log("未找到归档成绩单: ID=" + reportCardId);
            }
            return reportCard;
        } catch (InterruptedException e) {
            log("错误：获取归档成绩单过程中断");
            return null;
        }
    }
    
    /**
     * 同步数据到SMOS服务器
     * @param data 要同步的数据
     * @return 如果同步成功返回true，否则返回false
     */
    public boolean syncData(Object data) {
        if (data == null) {
            log("错误：无法同步空数据");
            return false;
        }
        
        if (!checkConnection()) {
            log("错误：无法同步数据，未连接到SMOS服务器");
            return false;
        }
        
        log("正在同步数据到SMOS服务器: " + data.getClass().getSimpleName());
        
        // 模拟同步过程（有20%的概率同步失败）
        if (random.nextInt(100) < 20) {
            log("错误：数据同步失败，连接可能已中断");
            isConnected = false; // 模拟连接中断
            return false;
        }
        
        try {
            // 模拟同步操作
            Thread.sleep(1200);
            
            log("数据同步成功: " + data.getClass().getSimpleName());
            return true;
        } catch (InterruptedException e) {
            log("错误：数据同步过程中断");
            return false;
        }
    }
    
    /**
     * 模拟服务器故障
     */
    public void simulateServerFailure() {
        log("警告：模拟服务器故障");
        isAvailable = false;
        isConnected = false;
        
        // 模拟服务器恢复（在5-15秒后）
        new Thread(() -> {
            try {
                int recoveryTime = 5000 + random.nextInt(10000); // 5-15秒
                Thread.sleep(recoveryTime);
                
                isAvailable = true;
                log("服务器已恢复，可以重新连接");
            } catch (InterruptedException e) {
                log("服务器恢复过程被中断");
            }
        }).start();
    }
    
    /**
     * 获取服务器状态信息
     * @return 服务器状态字符串
     */
    public String getServerStatus() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder status = new StringBuilder();
        
        status.append("=== SMOS服务器状态 ===\n");
        status.append("服务器名称: ").append(serverName).append("\n");
        status.append("服务器地址: ").append(serverAddress).append(":").append(serverPort).append("\n");
        status.append("连接状态: ").append(isConnected ? "已连接" : "未连接").append("\n");
        status.append("服务器可用: ").append(isAvailable ? "是" : "否").append("\n");
        status.append("连接超时: ").append(connectionTimeout).append("ms\n");
        status.append("最大重试次数: ").append(maxRetryAttempts).append("\n");
        status.append("已归档成绩单数量: ").append(archivedReports.size()).append("\n");
        status.append("最后连接时间: ").append(connectionLog.isEmpty() ? "无" : connectionLog.get(connectionLog.size() - 1)).append("\n");
        
        return status.toString();
    }
    
    /**
     * 显示服务器状态
     */
    public void displayServerStatus() {
        System.out.println(getServerStatus());
    }
    
    /**
     * 获取已归档成绩单数量
     * @return 已归档成绩单数量
     */
    public int getArchivedReportCount() {
        return archivedReports.size();
    }
    
    /**
     * 获取所有已归档成绩单的ID列表
     * @return 成绩单ID列表
     */
    public List<Integer> getAllArchivedReportIds() {
        return new ArrayList<>(archivedReports.keySet());
    }
    
    /**
     * 清除所有已归档成绩单（仅用于测试）
     */
    public void clearAllArchivedReports() {
        archivedReports.clear();
        log("已清除所有归档成绩单");
    }
    
    /**
     * 设置服务器可用状态
     * @param available 是否可用
     */
    public void setAvailable(boolean available) {
        this.isAvailable = available;
        if (!available) {
            this.isConnected = false;
            log("服务器已设置为不可用状态");
        } else {
            log("服务器已设置为可用状态");
        }
    }
    
    /**
     * 设置连接超时时间
     * @param timeout 超时时间（毫秒）
     */
    public void setConnectionTimeout(int timeout) {
        if (timeout < 1000) {
            log("警告：连接超时时间太短，建议至少1000ms");
        }
        this.connectionTimeout = timeout;
        log("连接超时时间已设置为: " + timeout + "ms");
    }
    
    /**
     * 设置最大重试次数
     * @param maxRetryAttempts 最大重试次数
     */
    public void setMaxRetryAttempts(int maxRetryAttempts) {
        if (maxRetryAttempts < 1) {
            log("警告：最大重试次数必须至少为1");
            this.maxRetryAttempts = 1;
        } else {
            this.maxRetryAttempts = maxRetryAttempts;
        }
        log("最大重试次数已设置为: " + this.maxRetryAttempts);
    }
    
    /**
     * 重连服务器
     * @return 如果重连成功返回true，否则返回false
     */
    public boolean reconnect() {
        log("正在尝试重新连接到SMOS服务器...");
        
        for (int attempt = 1; attempt <= maxRetryAttempts; attempt++) {
            log("重连尝试 " + attempt + "/" + maxRetryAttempts);
            
            if (connect()) {
                log("重连成功");
                return true;
            }
            
            if (attempt < maxRetryAttempts) {
                try {
                    // 等待一段时间后重试
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log("重连等待过程中断");
                    return false;
                }
            }
        }
        
        log("重连失败，已达到最大重试次数");
        return false;
    }
    
    /**
     * 记录日志
     * @param message 日志消息
     */
    private void log(String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String timestamp = sdf.format(new Date());
        String logEntry = "[" + timestamp + "] " + message;
        
        connectionLog.add(logEntry);
        System.out.println(logEntry);
    }
    
    /**
     * 获取连接日志
     * @return 连接日志列表
     */
    public List<String> getConnectionLog() {
        return new ArrayList<>(connectionLog);
    }
    
    /**
     * 获取最近的连接日志（最后n条）
     * @param count 要获取的日志条数
     * @return 最近的连接日志
     */
    public List<String> getRecentConnectionLog(int count) {
        if (count <= 0) {
            return new ArrayList<>();
        }
        
        int startIndex = Math.max(0, connectionLog.size() - count);
        return new ArrayList<>(connectionLog.subList(startIndex, connectionLog.size()));
    }
    
    /**
     * 清除连接日志
     */
    public void clearConnectionLog() {
        connectionLog.clear();
        log("连接日志已清除");
    }
    
    /**
     * 测试服务器连接
     * @return 如果测试成功返回true，否则返回false
     */
    public boolean testConnection() {
        log("开始测试服务器连接...");
        
        if (!isAvailable) {
            log("测试失败：服务器不可用");
            return false;
        }
        
        boolean originalConnectionState = isConnected;
        
        // 断开连接（如果已连接）
        if (isConnected) {
            disconnect();
        }
        
        // 尝试连接
        boolean connectionResult = connect();
        
        // 恢复原始连接状态
        if (originalConnectionState && !connectionResult) {
            // 如果原本是连接的但测试连接失败，尝试重新连接
            reconnect();
        } else if (!originalConnectionState && connectionResult) {
            // 如果原本未连接但测试连接成功，断开连接
            disconnect();
        }
        
        log("服务器连接测试完成，结果: " + (connectionResult ? "成功" : "失败"));
        return connectionResult;
    }
    
    /**
     * 模拟后置条件：连接中断
     */
    public void simulateConnectionInterruption() {
        log("模拟后置条件：与SMOS服务器的连接中断");
        isConnected = false;
        isAvailable = false;
        
        // 模拟一段时间后恢复
        new Thread(() -> {
            try {
                Thread.sleep(8000); // 8秒后恢复
                isAvailable = true;
                log("连接已恢复，服务器重新可用");
            } catch (InterruptedException e) {
                log("连接恢复过程被中断");
            }
        }).start();
    }
    
    /**
     * 获取服务器基本信息
     * @return 服务器信息字符串
     */
    @Override
    public String toString() {
        return "SMOSServer{" +
                "serverName='" + serverName + '\'' +
                ", serverAddress='" + serverAddress + '\'' +
                ", serverPort=" + serverPort +
                ", isConnected=" + isConnected +
                ", isAvailable=" + isAvailable +
                ", archivedReports=" + archivedReports.size() +
                '}';
    }
}