/**
 * AgencyOperator类表示机构操作员，包含操作员ID、名称和登录状态等属性。
 * 用于验证操作员权限，确保只有已登录的操作员可以执行评论修改操作。
 */
public class AgencyOperator {
    private int operatorId;
    private String operatorName;
    private boolean isLoggedIn;
    
    /**
     * 构造函数，初始化操作员。
     * @param operatorId 操作员唯一标识
     * @param operatorName 操作员名称
     */
    public AgencyOperator(int operatorId, String operatorName) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.isLoggedIn = false; // 默认未登录
    }
    
    /**
     * 获取操作员ID。
     * @return 操作员ID
     */
    public int getOperatorId() {
        return operatorId;
    }
    
    /**
     * 设置操作员ID。
     * @param operatorId 新的操作员ID
     */
    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }
    
    /**
     * 获取操作员名称。
     * @return 操作员名称
     */
    public String getOperatorName() {
        return operatorName;
    }
    
    /**
     * 设置操作员名称。
     * @param operatorName 新的操作员名称
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    
    /**
     * 检查操作员是否已登录。
     * @return 如果操作员已登录则返回true，否则返回false
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * 模拟操作员登录过程。
     * @param username 用户名
     * @param password 密码
     * @return 如果登录成功则返回true，否则返回false
     */
    public boolean login(String username, String password) {
        // 模拟登录验证逻辑
        // 在实际应用中，这里应该连接认证服务器进行验证
        if (username != null && password != null && 
            !username.trim().isEmpty() && !password.trim().isEmpty()) {
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }
    
    /**
     * 模拟操作员登出过程。
     */
    public void logout() {
        this.isLoggedIn = false;
    }
    
    /**
     * 验证操作员是否有权限执行评论修改操作。
     * 根据用例要求，进入条件是"机构已登录"。
     * @return 如果操作员已登录则返回true，否则返回false
     */
    public boolean canModifyComment() {
        return isLoggedIn;
    }
    
    /**
     * 模拟服务器连接检查。
     * 用于处理"服务器连接中断"的退出条件。
     * @return 如果服务器连接正常则返回true，否则返回false
     */
    public boolean checkServerConnection() {
        // 模拟服务器连接检查
        // 在实际应用中，这里应该尝试连接服务器
        return true; // 假设连接正常
    }
    
    /**
     * 获取操作员信息字符串表示。
     * @return 操作员信息字符串
     */
    @Override
    public String toString() {
        return "AgencyOperator [ID=" + operatorId + 
               ", Name=" + operatorName + 
               ", LoggedIn=" + isLoggedIn + "]";
    }
}