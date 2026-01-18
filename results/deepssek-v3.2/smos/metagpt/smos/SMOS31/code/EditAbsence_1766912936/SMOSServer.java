import java.util.Random;

/**
 * SMOSServer类 - 模拟与SMOS服务器的连接和通信
 * 处理数据发送、接收和连接中断的场景
 */
public class SMOSServer {
    private String serverAddress;
    private int serverPort;
    private boolean isConnected;
    private Random random;
    
    /**
     * 默认构造函数 - 使用默认服务器配置
     */
    public SMOSServer() {
        this.serverAddress = "smos.education.gov";
        this.serverPort = 8080;
        this.isConnected = false;
        this.random = new Random();
    }
    
    /**
     * 自定义配置构造函数
     * @param serverAddress 服务器地址
     * @param serverPort 服务器端口
     */
    public SMOSServer(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.isConnected = false;
        this.random = new Random();
    }
    
    /**
     * 连接到SMOS服务器
     * 模拟连接过程，有一定几率连接失败
     * @return 连接是否成功
     */
    public boolean connect() {
        System.out.println("正在连接到SMOS服务器 " + serverAddress + ":" + serverPort + "...");
        
        // 模拟连接延迟
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            System.out.println("连接过程被中断。");
            return false;
        }
        
        // 模拟80%的连接成功率
        if (random.nextDouble() < 0.8) {
            isConnected = true;
            System.out.println("成功连接到SMOS服务器。");
            return true;
        } else {
            System.out.println("错误：无法连接到SMOS服务器。请检查网络连接。");
            return false;
        }
    }
    
    /**
     * 发送数据到服务器
     * @param data 要发送的数据（JSON格式）
     * @return 发送是否成功
     */
    public boolean sendData(String data) {
        if (!isConnected) {
            System.out.println("错误：未连接到服务器，无法发送数据。");
            return false;
        }
        
        if (data == null || data.trim().isEmpty()) {
            System.out.println("错误：发送数据不能为空。");
            return false;
        }
        
        System.out.println("正在向SMOS服务器发送数据：" + data);
        
        // 模拟数据传输延迟
        try {
            Thread.sleep(500);
            
            // 模拟30%的传输失败率（包括连接中断）
            if (random.nextDouble() < 0.3) {
                // 模拟连接突然中断
                simulateConnectionInterruption();
                return false;
            }
            
            // 模拟服务器响应
            System.out.println("服务器响应：数据接收成功，已更新缺勤记录。");
            return true;
            
        } catch (InterruptedException e) {
            System.out.println("数据传输过程被中断。");
            return false;
        }
    }
    
    /**
     * 模拟连接中断场景
     */
    private void simulateConnectionInterruption() {
        System.out.println("警告：检测到网络不稳定...");
        
        // 模拟不同类型的连接中断
        int interruptionType = random.nextInt(3);
        switch (interruptionType) {
            case 0:
                System.out.println("错误：与SMOS服务器的连接意外中断。");
                System.out.println("可能原因：网络超时");
                break;
            case 1:
                System.out.println("错误：服务器无响应。");
                System.out.println("可能原因：服务器维护或过载");
                break;
            case 2:
                System.out.println("错误：数据包丢失。");
                System.out.println("可能原因：网络质量差");
                break;
        }
        
        isConnected = false;
    }
    
    /**
     * 从服务器断开连接
     */
    public void disconnect() {
        if (isConnected) {
            System.out.println("正在从SMOS服务器断开连接...");
            isConnected = false;
            System.out.println("已成功断开连接。");
        } else {
            System.out.println("未连接到服务器，无需断开。");
        }
    }
    
    /**
     * 检查服务器是否可访问（心跳检测）
     * @return 服务器是否可达
     */
    public boolean pingServer() {
        System.out.println("向SMOS服务器发送心跳检测...");
        
        // 模拟心跳检测
        try {
            Thread.sleep(200);
            
            // 模拟90%的成功率
            if (random.nextDouble() < 0.9) {
                System.out.println("服务器响应正常。");
                return true;
            } else {
                System.out.println("服务器无响应。");
                return false;
            }
        } catch (InterruptedException e) {
            System.out.println("心跳检测被中断。");
            return false;
        }
    }
    
    /**
     * 模拟管理员中断操作
     * 根据用例要求，管理员可以中断操作
     */
    public void interruptByAdministrator() {
        System.out.println("管理员中断了服务器操作。");
        disconnect();
    }
    
    /**
     * 重连机制
     * @param maxRetries 最大重试次数
     * @return 重连是否成功
     */
    public boolean reconnect(int maxRetries) {
        System.out.println("尝试重新连接到SMOS服务器...");
        
        for (int i = 1; i <= maxRetries; i++) {
            System.out.println("重试第" + i + "次...");
            
            if (connect()) {
                System.out.println("重连成功。");
                return true;
            }
            
            // 等待后重试
            try {
                Thread.sleep(1000 * i); // 递增等待时间
            } catch (InterruptedException e) {
                System.out.println("重试过程被中断。");
                return false;
            }
        }
        
        System.out.println("已达到最大重试次数(" + maxRetries + ")，重连失败。");
        return false;
    }
    
    /**
     * 验证服务器配置
     * @return 配置是否有效
     */
    public boolean validateConfiguration() {
        if (serverAddress == null || serverAddress.trim().isEmpty()) {
            System.out.println("错误：服务器地址不能为空。");
            return false;
        }
        
        if (serverPort <= 0 || serverPort > 65535) {
            System.out.println("错误：服务器端口号无效。");
            return false;
        }
        
        return true;
    }
    
    // Getter和Setter方法
    
    public String getServerAddress() {
        return serverAddress;
    }
    
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
    
    public int getServerPort() {
        return serverPort;
    }
    
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    
    public boolean isConnected() {
        return isConnected;
    }
    
    /**
     * 获取服务器状态信息
     * @return 格式化后的状态信息
     */
    public String getStatus() {
        return String.format("SMOS服务器[地址: %s:%d, 连接状态: %s]", 
                           serverAddress, serverPort, isConnected ? "已连接" : "未连接");
    }
    
    @Override
    public String toString() {
        return String.format("SMOSServer{serverAddress='%s', serverPort=%d, isConnected=%s}",
                           serverAddress, serverPort, isConnected);
    }
}